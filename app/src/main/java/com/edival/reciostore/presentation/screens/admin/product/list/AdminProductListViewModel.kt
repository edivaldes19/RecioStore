package com.edival.reciostore.presentation.screens.admin.product.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.useCase.products.ProductsUseCase
import com.edival.reciostore.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminProductListViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var productResponse by mutableStateOf<Resource<List<Product>>?>(null)
        private set
    var productDeleteResponse by mutableStateOf<Resource<Unit>?>(null)
        private set

    init {
        savedStateHandle.get<String>("category")?.let { ctgStr ->
            Category.fromJson(ctgStr).also { category ->
                category.id?.let { id ->
                    viewModelScope.launch {
                        productResponse = Resource.Loading
                        productsUseCase.getProductsByCategoryUseCase(id).collect { result ->
                            productResponse = result
                        }
                    }
                }
            }
        }
    }

    fun deleteProduct(idProduct: String?): Job = viewModelScope.launch {
        if (!idProduct.isNullOrBlank()) {
            productDeleteResponse = Resource.Loading
            productsUseCase.deleteProductUseCase(idProduct).also { result ->
                productDeleteResponse = result
            }
        }
    }

    fun resetForm() {
        productDeleteResponse = null
    }
}