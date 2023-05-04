package com.edival.reciostore.presentation.screens.client.product.list.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.client.product.list.ClientProductListViewModel

@Composable
fun GetProducts(
    navHostController: NavHostController,
    padding: PaddingValues,
    vm: ClientProductListViewModel = hiltViewModel()
) {
    when (val response = vm.productsResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            ClientProductListContent(
                navHostController = navHostController, padding = padding, products = response.data
            )
        }

        is Resource.Failure -> Toast.makeText(
            LocalContext.current, response.message, Toast.LENGTH_SHORT
        ).show()

        else -> {
            response?.let {
                Toast.makeText(LocalContext.current, R.string.unknown_error, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}