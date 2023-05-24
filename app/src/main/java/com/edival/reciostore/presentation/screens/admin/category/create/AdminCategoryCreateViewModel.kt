package com.edival.reciostore.presentation.screens.admin.category.create

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.useCase.categories.CategoriesUseCase
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.screens.admin.category.AdminCategoryState
import com.edival.reciostore.presentation.screens.admin.category.mapper.toCategory
import com.edival.reciostore.presentation.util.ComposeFileProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminCategoryCreateViewModel @Inject constructor(private val categoriesUseCase: CategoriesUseCase) :
    ViewModel() {
    var state by mutableStateOf(AdminCategoryState())
        private set
    var categoryResponse by mutableStateOf<Resource<Category>?>(null)
        private set
    var enabledBtn by mutableStateOf(true)
        private set
    var errorMessage by mutableStateOf("")
        private set

    fun createCategory(ctx: Context): Job = viewModelScope.launch {
        enabledBtn = false
        categoryResponse = Resource.Loading
        ComposeFileProvider.createFilesFromUris(
            ctx, listOf(state.imgSelected!!)
        ) { zipFiles, errMsg ->
            when {
                !errMsg.isNullOrBlank() -> categoryResponse = Resource.Failure(errMsg)
                zipFiles.isNotEmpty() -> {
                    categoriesUseCase.createCategoryUseCase(
                        state.toCategory(), zipFiles.first().toUri()
                    ).also { result -> categoryResponse = result }
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

    fun onImageInput(uri: Uri) {
        state = state.copy(imgSelected = uri)
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

            state.imgSelected == null -> {
                errorMessage = ctx.getString(R.string.image_is_required)
                isValid(false)
            }

            else -> isValid(true)
        }
    }

    fun clearForm() {
        categoryResponse = null
        enabledBtn = true
    }
}