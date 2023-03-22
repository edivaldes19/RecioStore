package com.edival.reciostore.presentation.screens.roles

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.presentation.screens.roles.components.RolesContent

@Composable
fun RolesScreen(navHostController: NavHostController) {
    Scaffold(content = { paddingValues -> RolesContent(paddingValues = paddingValues) })
}