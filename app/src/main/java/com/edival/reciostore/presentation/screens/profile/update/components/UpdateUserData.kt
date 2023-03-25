package com.edival.reciostore.presentation.screens.profile.update.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.profile.update.ProfileUpdateViewModel

@Composable
fun UpdateUserData(vm: ProfileUpdateViewModel = hiltViewModel()) {
    when (val response = vm.updateResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> Toast.makeText(
            LocalContext.current,
            stringResource(R.string.profile_updated_successfully),
            Toast.LENGTH_SHORT
        ).show()
        is Resource.Failure -> Toast.makeText(
            LocalContext.current, response.message, Toast.LENGTH_SHORT
        ).show()
        else -> {}
    }
}