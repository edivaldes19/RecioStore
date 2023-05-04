package com.edival.reciostore.presentation.screens.admin.category.create.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.admin.category.create.AdminCategoryCreateViewModel

@Composable
fun CreateCategory(vm: AdminCategoryCreateViewModel = hiltViewModel()) {
    when (val response = vm.categoryResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            vm.clearForm(true)
            Toast.makeText(
                LocalContext.current,
                stringResource(R.string.category_created_successfully),
                Toast.LENGTH_SHORT
            ).show()
        }

        is Resource.Failure -> {
            vm.clearForm(false)
            Toast.makeText(LocalContext.current, response.message, Toast.LENGTH_SHORT).show()
        }

        else -> {
            response?.let {
                vm.clearForm(false)
                Toast.makeText(
                    LocalContext.current, stringResource(R.string.unknown_error), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}