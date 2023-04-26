package com.edival.reciostore.presentation.screens.admin.category.list.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.admin.category.list.AdminCategoryListViewModel

@Composable
fun DeleteCategory(vm: AdminCategoryListViewModel = hiltViewModel()) {
    when (val response = vm.deleteCategoryResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> Toast.makeText(
            LocalContext.current,
            stringResource(R.string.category_deleted_successfully),
            Toast.LENGTH_SHORT
        ).show()

        is Resource.Failure -> Toast.makeText(
            LocalContext.current, response.message, Toast.LENGTH_SHORT
        ).show()

        else -> {}
    }
}