package com.edival.reciostore.presentation.screens.admin.category.create.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.admin.category.create.AdminCategoryCreateViewModel

@Composable
fun CreateCategory(
    navHostController: NavHostController, vm: AdminCategoryCreateViewModel = hiltViewModel()
) {
    when (val response = vm.categoryResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            LaunchedEffect(Unit) {
                vm.clearForm()
                navHostController.popBackStack()
            }
            Toast.makeText(
                LocalContext.current, R.string.category_created_successfully, Toast.LENGTH_SHORT
            ).show()
        }

        is Resource.Failure -> {
            vm.clearForm()
            Toast.makeText(LocalContext.current, response.message, Toast.LENGTH_SHORT).show()
        }

        else -> {
            response?.let {
                vm.clearForm()
                Toast.makeText(LocalContext.current, R.string.unknown_error, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}