package com.edival.reciostore.presentation.screens.client.product.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.useCase.products.ProductsUseCase
import com.edival.reciostore.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientProductListViewModel @Inject constructor(private val productsUseCase: ProductsUseCase) :
    ViewModel() {
    var search by mutableStateOf("")
        private set
    var productsResponse by mutableStateOf<Resource<List<Product>>?>(null)
        private set

    init {
        viewModelScope.launch {
            productsResponse = Resource.Loading
            productsUseCase.getProductsUseCase().collect { result ->
                productsResponse = result
            }
        }
    }

    fun getProductsByName(name: String): Job = viewModelScope.launch {
        productsResponse = Resource.Loading
        productsUseCase.getProductsByNameUseCase(name).collect { result ->
            productsResponse = result
        }
    }

    fun onSearchInput(query: String) {
        search = query
    }
}