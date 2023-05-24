package com.edival.reciostore.presentation.screens.admin.product.update.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.admin.product.update.AdminProductUpdateViewModel

@Composable
fun DownloadProdImages(vm: AdminProductUpdateViewModel = hiltViewModel()) {
    when (val response = vm.downloadProdImagesResponse) {
        Resource.Loading -> DefaultProgressBar(R.string.downloading_images)
        is Resource.Success -> {
            vm.resetForm()
            Toast.makeText(
                LocalContext.current, R.string.images_downloaded_successfully, Toast.LENGTH_SHORT
            ).show()
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