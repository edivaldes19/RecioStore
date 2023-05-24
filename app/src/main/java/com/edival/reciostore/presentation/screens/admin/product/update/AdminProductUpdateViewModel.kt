package com.edival.reciostore.presentation.screens.admin.product.update

import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.core.net.toUri
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminProductUpdateViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(AdminProductState())
        private set
    var productResponse by mutableStateOf<Resource<Product>?>(null)
        private set
    var downloadProdImagesResponse by mutableStateOf<Resource<Unit>?>(null)
        private set
    var uriList = mutableStateListOf<Uri>()
        private set
    var prevImages = mutableStateListOf<String>()
        private set
    var enabledBtn by mutableStateOf(true)
        private set
    var errorMessage by mutableStateOf("")
        private set
    private var idProduct: String? = null

    init {
        savedStateHandle.get<String>("product")?.let { productStr ->
            Product.fromJson(productStr).also { product ->
                idProduct = product.id
                prevImages = product.phi?.map { it.img_url.orEmpty() }?.toMutableStateList()
                    ?: mutableStateListOf()
                state = state.copy(
                    name = product.name.orEmpty(),
                    description = product.description.orEmpty(),
                    price = product.price,
                    id_category = product.id_category.orEmpty(),
                    phi = product.phi
                )
            }
        }
    }

    fun updateProduct(ctx: Context): Job = viewModelScope.launch {
        enabledBtn = false
        productResponse = Resource.Loading
        if (uriList.isNotEmpty()) {
            ComposeFileProvider.createFilesFromUris(ctx, uriList) { zipFiles, errMsg ->
                when {
                    !errMsg.isNullOrBlank() -> productResponse = Resource.Failure(errMsg)
                    zipFiles.isNotEmpty() -> {
                        productsUseCase.updateProductUseCase(idProduct!!,
                            state.toProduct(),
                            zipFiles.map { file -> file.toUri() })
                            .also { result -> productResponse = result }
                    }
                }
            }
        } else {
            productsUseCase.updateProductUseCase(idProduct!!, state.toProduct(), null)
                .also { result -> productResponse = result }
        }
    }

    fun downloadProdImages(ctx: Context): Job = viewModelScope.launch {
        if (prevImages.isNotEmpty()) {
            enabledBtn = false
            downloadProdImagesResponse = Resource.Loading
            productsUseCase.downloadProdImagesUseCase(ctx, state.name, prevImages).also { result ->
                downloadProdImagesResponse = result
            }
        } else errorMessage = ctx.getString(R.string.theres_no_image_to_download)
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

    fun pickImage(ctx: Context, launcher: ManagedActivityResultLauncher<String, Uri?>) {
        if (uriList.size < 5) launcher.launch(Config.IMAGES_MT)
        else errorMessage = ctx.getString(R.string.maximum_imgs)
    }

    fun addImg(uri: Uri) {
        if (!uriList.contains(uri)) uriList.add(uri)
        else updateImg(uri)
    }

    private fun updateImg(uri: Uri) {
        val i = uriList.indexOf(uri)
        if (i != -1) uriList[i] = uri
    }

    fun deleteImg(uri: Uri) {
        val i = uriList.indexOf(uri)
        if (i != -1) uriList.removeAt(i)
    }

    fun showMsg(show: () -> Unit) {
        if (errorMessage.isNotBlank()) {
            show()
            errorMessage = ""
        }
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

            idProduct.isNullOrBlank() -> {
                errorMessage = ctx.getString(R.string.id_cannot_be_null)
                isValid(false)
            }

            else -> isValid(true)
        }
    }

    fun resetForm() {
        productResponse = null
        downloadProdImagesResponse = null
        enabledBtn = true
    }
}