package com.edival.reciostore.presentation.screens.client.product.listByCategory

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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientProductListByCategoryViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var productResponse by mutableStateOf<Resource<List<Product>>?>(null)
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
}