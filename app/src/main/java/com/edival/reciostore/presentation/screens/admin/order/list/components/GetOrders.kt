package com.edival.reciostore.presentation.screens.admin.order.list.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.admin.order.list.AdminOrderListViewModel

@Composable
fun GetOrders(
    navHostController: NavHostController,
    padding: PaddingValues,
    vm: AdminOrderListViewModel = hiltViewModel()
) {
    when (val response = vm.ordersResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> AdminOrderListContent(padding, response.data, navHostController)
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