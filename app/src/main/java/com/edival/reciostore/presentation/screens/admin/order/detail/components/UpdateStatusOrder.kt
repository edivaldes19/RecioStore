package com.edival.reciostore.presentation.screens.admin.order.detail.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.admin.order.detail.AdminOrderDetailViewModel

@Composable
fun UpdateStatusOrder(vm: AdminOrderDetailViewModel = hiltViewModel()) {
    when (val response = vm.ordersStatusResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            Toast.makeText(
                LocalContext.current, R.string.order_updated_successfully, Toast.LENGTH_SHORT
            ).show()
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