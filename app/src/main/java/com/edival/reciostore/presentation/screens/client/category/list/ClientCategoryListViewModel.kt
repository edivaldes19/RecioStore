package com.edival.reciostore.presentation.screens.client.category.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.useCase.categories.CategoriesUseCase
import com.edival.reciostore.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientCategoryListViewModel @Inject constructor(private val categoriesUseCase: CategoriesUseCase) :
    ViewModel() {
    var categoriesResponse by mutableStateOf<Resource<List<Category>>?>(null)
        private set

    init {
        viewModelScope.launch {
            categoriesResponse = Resource.Loading
            categoriesUseCase.getCategoriesUseCase().collect { data ->
                categoriesResponse = data
            }
        }
    }
}