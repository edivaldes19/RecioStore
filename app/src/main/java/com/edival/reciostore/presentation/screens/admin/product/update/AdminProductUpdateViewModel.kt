package com.edival.reciostore.presentation.screens.admin.product.update

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.useCase.products.ProductsUseCase
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.screens.admin.product.AdminProductState
import com.edival.reciostore.presentation.screens.admin.product.mapper.toProduct
import com.edival.reciostore.presentation.util.ComposeFileProvider
import com.edival.reciostore.presentation.util.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AdminProductUpdateViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(AdminProductState())
        private set
    var productResponse by mutableStateOf<Resource<Product>?>(null)
        private set
    var enabledBtn by mutableStateOf(true)
    var errorMessage by mutableStateOf("")
    val resultingActivityHandler = ResultingActivityHandler()
    private var files: MutableList<File> = mutableListOf()
    private var file1: File? = null
    private var file2: File? = null
    private var idProduct: String? = null

    init {
        savedStateHandle.get<String>("product")?.let { productStr ->
            Product.fromJson(productStr).also { product ->
                idProduct = product.id
                state = state.copy(
                    name = product.name.orEmpty(),
                    description = product.description.orEmpty(),
                    price = product.price,
                    id_category = product.id_category.orEmpty(),
                    img1 = product.img1,
                    img2 = product.img2
                )
            }
        }
    }

    fun updateProduct(): Job = viewModelScope.launch {
        idProduct?.let { id ->
            enabledBtn = false
            productResponse = Resource.Loading
            productsUseCase.updateProductUseCase(id, state.toProduct()).also { result ->
                productResponse = result
            }
        }
    }

    fun updateProductImages(): Job = viewModelScope.launch {
        idProduct?.let { id ->
            enabledBtn = false
            productResponse = Resource.Loading
            if (file1 == null && file2 == null) {
                productsUseCase.updateProductUseCase(id, state.toProduct()).also { result ->
                    productResponse = result
                }
            } else {
                file1?.let { f1 ->
                    files.add(f1)
                    state.images_to_update.add(0)
                }
                file2?.let { f2 ->
                    files.add(f2)
                    state.images_to_update.add(1)
                }
                productsUseCase.updateProductImagesUseCase(files.toList(), id, state.toProduct())
                    .also { result -> productResponse = result }
            }
            files.clear()
            file1 = null
            file2 = null
            state.images_to_update.clear()
        }
    }

    fun pickImage(ctx: Context, imageNumber: Int): Job = viewModelScope.launch {
        resultingActivityHandler.getContent(Config.IMAGES_MT).also { result ->
            result?.let { uri ->
                when (imageNumber) {
                    1 -> {
                        state = state.copy(img1 = uri.toString())
                        file1 = ComposeFileProvider.createFileFromUri(ctx, uri)
                    }

                    2 -> {
                        state = state.copy(img2 = uri.toString())
                        file2 = ComposeFileProvider.createFileFromUri(ctx, uri)
                    }
                }
            }
        }
    }

    fun takePhoto(ctx: Context, imageNumber: Int): Job = viewModelScope.launch {
        resultingActivityHandler.takePicturePreview().also { result ->
            result?.let { bitmap ->
                when (imageNumber) {
                    1 -> {
                        state =
                            state.copy(img1 = ComposeFileProvider.getPathFromBitmap(ctx, bitmap))
                        file1 = File(state.img1!!)
                    }

                    2 -> {
                        state =
                            state.copy(img2 = ComposeFileProvider.getPathFromBitmap(ctx, bitmap))
                        file2 = File(state.img2!!)
                    }
                }
            }
        }
    }

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }

    fun onDescriptionInput(description: String) {
        state = state.copy(description = description)
    }

    fun onPriceInput(price: String) {
        state = state.copy(price = price.toDoubleOrNull() ?: 0.0)
    }

    fun validateForm(ctx: Context, isValid: (Boolean) -> Unit) {
        when {
            state.name.length < 5 || state.name.isBlank() -> {
                errorMessage = ctx.getString(R.string.invalid_name)
                isValid(false)
            }

            state.description.length < 5 || state.description.isBlank() -> {
                errorMessage = ctx.getString(R.string.invalid_description)
                isValid(false)
            }

            state.price == 0.0 -> {
                errorMessage = ctx.getString(R.string.invalid_price)
                isValid(false)
            }

            else -> isValid(true)
        }
    }
}