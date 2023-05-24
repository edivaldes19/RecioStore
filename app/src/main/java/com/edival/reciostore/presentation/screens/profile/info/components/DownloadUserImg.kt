package com.edival.reciostore.presentation.screens.profile.info.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.profile.info.ProfileInfoViewModel

@Composable
fun DownloadUserImg(vm: ProfileInfoViewModel = hiltViewModel()) {
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