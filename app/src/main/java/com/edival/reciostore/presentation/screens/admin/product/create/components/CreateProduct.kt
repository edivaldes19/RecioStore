package com.edival.reciostore.presentation.screens.admin.product.create.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.admin.product.create.AdminProductCreateViewModel

@Composable
fun CreateProduct(vm: AdminProductCreateViewModel = hiltViewModel()) {
    when (val response = vm.productResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            vm.clearForm(true)
            Toast.makeText(
                LocalContext.current,
                stringResource(R.string.product_created_successfully),
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