package com.edival.reciostore.presentation.screens.admin.category.update.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.admin.category.update.AdminCategoryUpdateViewModel

@Composable
fun UpdateCategory(vm: AdminCategoryUpdateViewModel = hiltViewModel()) {
    when (val response = vm.categoryResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            vm.enabledBtn = true
            Toast.makeText(
                LocalContext.current, R.string.category_updated_successfully, Toast.LENGTH_SHORT
            ).show()
        }

        is Resource.Failure -> {
            vm.enabledBtn = true
            Toast.makeText(LocalContext.current, response.message, Toast.LENGTH_SHORT).show()
        }

        else -> {
            response?.let {
                vm.enabledBtn = true
                Toast.makeText(LocalContext.current, R.string.unknown_error, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}