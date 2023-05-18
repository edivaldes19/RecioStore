package com.edival.reciostore.presentation.screens.client.product.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.model.ShoppingBagProduct
import com.edival.reciostore.domain.useCase.shopping_bag.ShoppingBagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientProductDetailViewModel @Inject constructor(
    private val shoppingBagUseCase: ShoppingBagUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var quantity by mutableStateOf(1)
        private set
    var totalPrice by mutableStateOf(0.0)
        private set
    var enabledBtnAdd by mutableStateOf(true)
        private set
    var enabledBtnSub by mutableStateOf(true)
        private set
    var product by mutableStateOf<Product?>(null)
        private set

    init {
        savedStateHandle.get<String>("product")?.let { prodStr ->
            product = Product.fromJson(prodStr)
            if (product != null && !product!!.id.isNullOrBlank()) {
                viewModelScope.launch {
                    shoppingBagUseCase.getProductBagByIdUseCase(product!!.id!!).also { result ->
                        quantity = result?.quantity ?: 1
                        totalPrice = product!!.price * quantity
                    }
                }
            }
        }
    }

    fun addItem() {
        product?.let { prod ->
            when {
                quantity < 10 -> {
                    quantity++
                    totalPrice = prod.price * quantity
                    enabledBtnAdd = true
                    enabledBtnSub = true
                }

                quantity == 10 -> {
                    enabledBtnAdd = false
                    enabledBtnSub = true
                }
            }
        }
    }

    fun subItem() {
        product?.let { prod ->
            when {
                quantity > 1 -> {
                    quantity--
                    totalPrice = prod.price * quantity
                    enabledBtnSub = true
                    enabledBtnAdd = true
                }

                quantity == 1 -> {
                    enabledBtnSub = false
                    enabledBtnAdd = true
                }
            }
        }
    }

    fun saveProductInBag(already: () -> Unit): Job = viewModelScope.launch {
        product?.let { prod ->
            prod.phi?.let { images ->
                val shoppingBagProduct = ShoppingBagProduct(
                    id = prod.id.orEmpty(),
                    name = prod.name.orEmpty(),
                    price = prod.price,
                    id_category = prod.id_category.orEmpty(),
                    img = images.first().img_url.orEmpty(),
                    quantity = quantity
                )
                shoppingBagUseCase.addProductBagUseCase(shoppingBagProduct).also { already() }
            }
        }
    }
}