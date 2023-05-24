package com.edival.reciostore.presentation.screens.admin.category.update

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
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
class AdminCategoryUpdateViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(AdminCategoryState())
        private set
    var categoryResponse by mutableStateOf<Resource<Category>?>(null)
        private set
    var downloadCtgImgResponse by mutableStateOf<Resource<Unit>?>(null)
        private set
    var errorMessage by mutableStateOf("")
        private set
    var enabledBtn by mutableStateOf(true)
        private set
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

    fun updateCategory(ctx: Context): Job = viewModelScope.launch {
        enabledBtn = false
        categoryResponse = Resource.Loading
        if (state.imgSelected != null) {
            ComposeFileProvider.createFilesFromUris(
                ctx, listOf(state.imgSelected!!)
            ) { zipFiles, errMsg ->
                when {
                    !errMsg.isNullOrBlank() -> categoryResponse = Resource.Failure(errMsg)
                    zipFiles.isNotEmpty() -> {
                        categoriesUseCase.updateCategoryUseCase(
                            idCategory!!, state.toCategory(), zipFiles.first().toUri()
                        ).also { result -> categoryResponse = result }
                    }
                }
            }
        } else {
            categoriesUseCase.updateCategoryUseCase(idCategory!!, state.toCategory(), null)
                .also { result -> categoryResponse = result }
        }
    }

    fun downloadCtgImg(ctx: Context): Job = viewModelScope.launch {
        if (!state.img.isNullOrBlank()) {
            enabledBtn = false
            downloadCtgImgResponse = Resource.Loading
            categoriesUseCase.downloadCtgImgUseCase(ctx, state.img!!).also { result ->
                downloadCtgImgResponse = result
            }
        } else errorMessage = ctx.getString(R.string.theres_no_image_to_download)
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

            idCategory.isNullOrBlank() -> {
                errorMessage = ctx.getString(R.string.id_cannot_be_null)
                isValid(false)
            }

            else -> isValid(true)
        }
    }

    fun resetForm() {
        categoryResponse = null
        downloadCtgImgResponse = null
        enabledBtn = true
    }
}