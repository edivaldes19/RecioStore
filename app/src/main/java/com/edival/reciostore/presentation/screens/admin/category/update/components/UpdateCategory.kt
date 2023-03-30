package com.edival.reciostore.presentation.screens.admin.category.update.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.admin.category.update.AdminCategoryUpdateViewModel

@Composable
fun UpdateCategory(
    navHostController: NavHostController, vm: AdminCategoryUpdateViewModel = hiltViewModel()
) {
    when (val response = vm.categoryResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            navHostController.popBackStack()
            Toast.makeText(
                LocalContext.current,
                stringResource(R.string.category_updated_successfully),
                Toast.LENGTH_SHORT
            ).show()
        }
        is Resource.Failure -> Toast.makeText(
            LocalContext.current, response.message, Toast.LENGTH_SHORT
        ).show()
        else -> {}
    }
}