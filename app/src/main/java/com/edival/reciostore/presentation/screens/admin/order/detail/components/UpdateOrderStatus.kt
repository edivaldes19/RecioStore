package com.edival.reciostore.presentation.screens.admin.order.detail.components

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.MainActivity
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.admin.order.detail.AdminOrderDetailViewModel

@Composable
fun UpdateOrderStatus(vm: AdminOrderDetailViewModel = hiltViewModel()) {
    val activity = LocalContext.current as? Activity
    when (val response = vm.ordersStatusResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            LaunchedEffect(Unit) {
                activity?.let { act ->
                    vm.resetForm()
                    act.finish()
                    act.startActivity(Intent(act, MainActivity::class.java))
                    Toast.makeText(act, R.string.order_updated_successfully, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        is Resource.Failure -> {
            vm.resetForm()
            Toast.makeText(LocalContext.current, response.message, Toast.LENGTH_SHORT).show()
        }

        else -> {
            response?.let {
                vm.resetForm()
                Toast.makeText(LocalContext.current, R.string.unknown_error, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}