package com.edival.reciostore.presentation.screens.admin.user.detail

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.admin.user.detail.components.AdminUserDetailContent

@Composable
fun AdminUserDetailScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(
            titleRes = R.string.user_info, navHostController = navHostController, upAvailable = true
        )
    }) { padding -> AdminUserDetailContent(padding) }
}