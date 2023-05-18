package com.edival.reciostore.presentation.screens.loading

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.presentation.screens.loading.components.GetInfoLoading

@Composable
fun LoadingAppScreen(navHostController: NavHostController) {
    Scaffold { padding -> GetInfoLoading(padding, navHostController) }
}