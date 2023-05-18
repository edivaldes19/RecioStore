package com.edival.reciostore.presentation.screens.client.shopping_bag

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.ShoppingBagProduct
import com.edival.reciostore.domain.useCase.shopping_bag.ShoppingBagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientShoppingBagViewModel @Inject constructor(private val shoppingBagUseCase: ShoppingBagUseCase) :
    ViewModel() {
    var shoppingBag = mutableStateListOf<ShoppingBagProduct>()
        private set
    var enabledBtnAdd by mutableStateOf(true)
        private set
    var enabledBtnSub by mutableStateOf(true)
        private set
    var total by mutableStateOf(0.0)
        private set

    init {
        viewModelScope.launch {
            shoppingBagUseCase.getProductsBagUseCase().collect { shoppingBagProducts ->
                shoppingBag.clear()
                shoppingBag.addAll(shoppingBagProducts)
                calculateTotal()
                Log.d("getShoppingBag", "$shoppingBagProducts")
            }
        }
    }

    private fun calculateTotal() {
        total = 0.0
        shoppingBag.forEach { bagProduct ->
            total += (bagProduct.price * bagProduct.quantity)
        }
    }

    fun addItem(product: ShoppingBagProduct): Job = viewModelScope.launch {
        when {
            product.quantity < 10 -> {
                product.quantity++
                shoppingBagUseCase.addProductBagUseCase(product)
                calculateTotal()
                enabledBtnAdd = true
                enabledBtnSub = true
            }

            product.quantity == 10 -> {
                enabledBtnAdd = false
                enabledBtnSub = true
            }
        }
    }

    fun subItem(product: ShoppingBagProduct): Job = viewModelScope.launch {
        when {
            product.quantity > 1 -> {
                product.quantity--
                shoppingBagUseCase.addProductBagUseCase(product)
                calculateTotal()
                enabledBtnSub = true
                enabledBtnAdd = true
            }

            product.quantity == 1 -> {
                enabledBtnSub = false
                enabledBtnAdd = true
            }
        }
    }

    fun deleteItem(idProduct: String): Job = viewModelScope.launch {
        shoppingBagUseCase.deleteProductBagUseCase(idProduct)
        calculateTotal()
    }
}