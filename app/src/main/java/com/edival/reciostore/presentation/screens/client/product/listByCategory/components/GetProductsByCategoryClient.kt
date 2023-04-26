package com.edival.reciostore.presentation.screens.client.product.listByCategory.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.client.product.listByCategory.ClientProductListByCategoryViewModel

@Composable
fun GetProductsByCategoryClient(
    navHostController: NavHostController,
    padding: PaddingValues,
    vm: ClientProductListByCategoryViewModel = hiltViewModel()
) {
    when (val response = vm.productResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            ClientProductListByCategoryContent(
                navHostController = navHostController, padding = padding, products = response.data
            )
        }

        is Resource.Failure -> Toast.makeText(
            LocalContext.current, response.message, Toast.LENGTH_SHORT
        ).show()

        else -> {}
    }
}