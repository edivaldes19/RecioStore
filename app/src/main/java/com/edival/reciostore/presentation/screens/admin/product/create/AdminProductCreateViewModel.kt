package com.edival.reciostore.presentation.screens.admin.product.create

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.Category
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
class AdminProductCreateViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(AdminProductState())
        private set
    var productResponse by mutableStateOf<Resource<Product>?>(null)
        private set
    var errorMessage by mutableStateOf("")
    val resultingActivityHandler = ResultingActivityHandler()
    var categoryName: String? = null
    private var file1: File? = null
    private var file2: File? = null

    init {
        savedStateHandle.get<String>("category")?.let { ctgStr ->
            Category.fromJson(ctgStr).also { category ->
                categoryName = category.name
                state = state.copy(id_category = category.id.orEmpty())
            }
        }
    }

    fun createProduct(): Job = viewModelScope.launch {
        if (file1 != null && file2 != null) {
            productResponse = Resource.Loading
            productsUseCase.createProductUseCase(listOf(file1!!, file2!!), state.toProduct())
                .also { result -> productResponse = result }
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
        state = state.copy(price = price.toDouble())
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
        }
        isValid(true)
    }
}