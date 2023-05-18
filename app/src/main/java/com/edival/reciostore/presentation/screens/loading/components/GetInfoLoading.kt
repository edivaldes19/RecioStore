package com.edival.reciostore.presentation.screens.loading.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.screens.loading.LoadingAppViewModel

@Composable
fun GetInfoLoading(
    padding: PaddingValues,
    navHostController: NavHostController,
    vm: LoadingAppViewModel = hiltViewModel()
) {
    when (val response = vm.infoResponse) {
        Resource.Loading -> DefaultProgressBar(R.string.getting_user_session)
        is Resource.Success -> {
            LoadingAppContent(padding, response.data) { route ->
                LaunchedEffect(Unit) {
                    navHostController.navigate(route) {
                        popUpTo(Graph.AUTH) { inclusive = true }
                    }
                }
            }
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