package com.edival.reciostore.presentation.screens.admin.product.create

import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminProductCreateViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(AdminProductState())
        private set
    var productResponse by mutableStateOf<Resource<Product>?>(null)
        private set
    var enabledBtn by mutableStateOf(true)
        private set
    var errorMessage by mutableStateOf("")
        private set
    var categoryName by mutableStateOf<String?>(null)
        private set
    var uriList = mutableStateListOf<Uri>()
        private set

    init {
        savedStateHandle.get<String>("category")?.let { ctgStr ->
            Category.fromJson(ctgStr).also { category ->
                categoryName = category.name
                state = state.copy(id_category = category.id.orEmpty())
            }
        }
    }

    fun createProduct(ctx: Context): Job = viewModelScope.launch {
        enabledBtn = false
        productResponse = Resource.Loading
        ComposeFileProvider.createFilesFromUris(ctx, uriList) { zipFiles, errMsg ->
            when {
                !errMsg.isNullOrBlank() -> productResponse = Resource.Failure(errMsg)
                zipFiles.isNotEmpty() -> {
                    productsUseCase.createProductUseCase(
                        state.toProduct(),
                        zipFiles.map { file -> file.toUri() })
                        .also { result -> productResponse = result }
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

            state.id_category.isBlank() -> {
                errorMessage = ctx.getString(R.string.id_cannot_be_null)
                isValid(false)
            }

            uriList.isEmpty() -> {
                errorMessage = ctx.getString(R.string.msg_least_one_image)
                isValid(false)
            }

            else -> isValid(true)
        }
    }

    fun clearForm() {
        productResponse = null
        enabledBtn = true
    }
}