package com.edival.reciostore.presentation.screens.client.category.list.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.client.category.list.ClientCategoryListViewModel

@Composable
fun GetCategories(
    navHostController: NavHostController,
    padding: PaddingValues,
    vm: ClientCategoryListViewModel = hiltViewModel()
) {
    when (val response = vm.categoriesResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> ClientCategoryListContent(navHostController, padding, response.data)
        is Resource.Failure -> Toast.makeText(
            LocalContext.current, response.message, Toast.LENGTH_SHORT
        ).show()

        else -> {}
    }
}