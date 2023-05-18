package com.edival.reciostore.presentation.screens.admin.product.create

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.useCase.products.ProductsUseCase
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.screens.admin.product.AdminProductState
import com.edival.reciostore.presentation.screens.admin.product.mapper.toProduct
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
    var categoryName by mutableStateOf<String?>(null)
        private set
    var errorMessage by mutableStateOf("")
    var imgUri1 by mutableStateOf<Uri?>(null)
    var imgUri2 by mutableStateOf<Uri?>(null)

    init {
        savedStateHandle.get<String>("category")?.let { ctgStr ->
            Category.fromJson(ctgStr).also { category ->
                categoryName = category.name
                state = state.copy(id_category = category.id.orEmpty())
            }
        }
    }

    fun createProduct(): Job = viewModelScope.launch {
        enabledBtn = false
        productResponse = Resource.Loading
        productsUseCase.createProductUseCase(state.toProduct(), listOf(imgUri1!!, imgUri2!!))
            .also { result -> productResponse = result }
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

    fun onImage1Input(url: String) {
        state = state.copy(img1 = url)
    }

    fun onImage2Input(url: String) {
        state = state.copy(img2 = url)
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

            imgUri1 == null || imgUri2 == null -> {
                errorMessage = ctx.getString(R.string.both_images_are_required)
                isValid(false)
            }

            else -> isValid(true)
        }
    }

    fun clearForm(isOnlyForm: Boolean) {
        if (isOnlyForm) {
            state = state.copy(name = "", description = "", price = 0.0, img1 = null, img2 = null)
            productResponse = null
        }
        enabledBtn = true
    }
}