package com.edival.reciostore.presentation.screens.admin.user.detail.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.admin.user.detail.AdminUserDetailViewModel

@Composable
fun DownloadUserImgByDetail(vm: AdminUserDetailViewModel = hiltViewModel()) {
    when (val response = vm.downloadUserImgResponse) {
        Resource.Loading -> DefaultProgressBar(R.string.downloading_image)
        is Resource.Success -> {
            vm.resetForm()
            Toast.makeText(
                LocalContext.current, R.string.image_downloaded_successfully, Toast.LENGTH_SHORT
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