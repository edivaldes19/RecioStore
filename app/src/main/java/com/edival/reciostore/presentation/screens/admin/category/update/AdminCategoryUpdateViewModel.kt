package com.edival.reciostore.presentation.screens.admin.category.update

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.useCase.categories.CategoriesUseCase
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.screens.admin.category.AdminCategoryState
import com.edival.reciostore.presentation.screens.admin.category.mapper.toCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminCategoryUpdateViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(AdminCategoryState())
        private set
    var categoryResponse by mutableStateOf<Resource<Category>?>(null)
        private set
    var imgUri by mutableStateOf<Uri?>(null)
    var enabledBtn by mutableStateOf(true)
    var errorMessage by mutableStateOf("")
    private var idCategory: String? = null

    init {
        savedStateHandle.get<String>("category")?.let { categoryStr ->
            Category.fromJson(categoryStr).also { category ->
                Log.d("AdminCategoryUpdateViewModel", "CATEGORY: $category")
                idCategory = category.id
                state = state.copy(
                    name = category.name.orEmpty(),
                    description = category.description.orEmpty(),
                    img = category.img
                )
            }
        }
    }

    fun updateCategory(): Job = viewModelScope.launch {
        if (!idCategory.isNullOrBlank()) {
            enabledBtn = false
            categoryResponse = Resource.Loading
            categoriesUseCase.updateCategoryUseCase(idCategory!!, state.toCategory(), imgUri)
                .also { result -> categoryResponse = result }
        }
    }

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }

    fun onDescriptionInput(description: String) {
        state = state.copy(description = description)
    }

    fun onImageInput(url: String) {
        state = state.copy(imgSelected = url)
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

            else -> isValid(true)
        }
    }
}