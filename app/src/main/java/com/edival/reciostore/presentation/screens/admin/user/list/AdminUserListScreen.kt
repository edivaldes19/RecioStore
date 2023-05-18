package com.edival.reciostore.presentation.screens.admin.user.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.presentation.screens.admin.user.list.components.GetUsers

@Composable
fun AdminUserListScreen(navHostController: NavHostController) {
    Scaffold { padding -> GetUsers(navHostController, padding) }
}