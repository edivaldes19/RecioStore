package com.edival.reciostore.presentation.screens.admin.product.update

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
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
class AdminProductUpdateViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(AdminProductState())
        private set
    var productResponse by mutableStateOf<Resource<Product>?>(null)
        private set
    var enabledBtn by mutableStateOf(true)
    var errorMessage by mutableStateOf("")
    var imgUri1 by mutableStateOf<Uri?>(null)
    var imgUri2 by mutableStateOf<Uri?>(null)
    private var idProduct: String? = null

    init {
        savedStateHandle.get<String>("product")?.let { productStr ->
            Product.fromJson(productStr).also { product ->
                idProduct = product.id
                product.phi?.let { images ->
                    state.copy(img1 = images.first().img_url, img2 = images.last().img_url)
                }
                state = state.copy(
                    name = product.name.orEmpty(),
                    description = product.description.orEmpty(),
                    price = product.price,
                    id_category = product.id_category.orEmpty()
                )
            }
        }
    }

    fun updateProduct(): Job = viewModelScope.launch {
        enabledBtn = false
        productResponse = Resource.Loading
        when {
            imgUri1 != null && imgUri2 != null -> {
                productsUseCase.updateProductUseCase(
                    idProduct!!, state.toProduct(), listOf(imgUri1!!, imgUri2!!)
                ).also { result -> productResponse = result }
            }

            imgUri1 != null && imgUri2 == null -> {
                productsUseCase.updateProductUseCase(
                    idProduct!!, state.toProduct(), listOf(imgUri1!!)
                ).also { result -> productResponse = result }
            }

            imgUri1 == null && imgUri2 != null -> {
                productsUseCase.updateProductUseCase(
                    idProduct!!, state.toProduct(), listOf(imgUri2!!)
                ).also { result -> productResponse = result }
            }

            else -> {
                productsUseCase.updateProductUseCase(idProduct!!, state.toProduct(), null)
                    .also { result -> productResponse = result }
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

            idProduct.isNullOrBlank() -> {
                errorMessage = ctx.getString(R.string.id_cannot_be_null)
                isValid(false)
            }

            else -> isValid(true)
        }
    }
}