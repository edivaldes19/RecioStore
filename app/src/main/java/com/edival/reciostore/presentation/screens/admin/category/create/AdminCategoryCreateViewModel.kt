package com.edival.reciostore.presentation.screens.admin.category.create

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.useCase.categories.CategoriesUseCase
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.screens.admin.category.AdminCategoryState
import com.edival.reciostore.presentation.screens.admin.category.mapper.toCategory
import com.edival.reciostore.presentation.util.ComposeFileProvider
import com.edival.reciostore.presentation.util.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AdminCategoryCreateViewModel @Inject constructor(private val categoriesUseCase: CategoriesUseCase) :
    ViewModel() {
    var state by mutableStateOf(AdminCategoryState())
        private set
    var categoryResponse by mutableStateOf<Resource<Category>?>(null)
        private set
    var errorMessage by mutableStateOf("")
    val resultingActivityHandler = ResultingActivityHandler()
    private var file: File? = null
    fun createCategory(): Job = viewModelScope.launch {
        file?.let { photo ->
            categoryResponse = Resource.Loading
            categoriesUseCase.createCategoryUseCase(photo, state.toCategory()).also { result ->
                categoryResponse = result
            }
        }
    }

    fun pickImage(ctx: Context): Job = viewModelScope.launch {
        resultingActivityHandler.getContent(Config.IMAGES_MT).also { result ->
            result?.let { uri ->
                state = state.copy(img = uri.toString())
                file = ComposeFileProvider.createFileFromUri(ctx, uri)
            }
        }
    }

    fun takePhoto(ctx: Context): Job = viewModelScope.launch {
        resultingActivityHandler.takePicturePreview().also { result ->
            result?.let { bitmap ->
                state = state.copy(img = ComposeFileProvider.getPathFromBitmap(ctx, bitmap))
                file = File(state.img!!)
            }
        }
    }

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }

    fun onDescriptionInput(description: String) {
        state = state.copy(description = description)
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
        }
        isValid(true)
    }
}