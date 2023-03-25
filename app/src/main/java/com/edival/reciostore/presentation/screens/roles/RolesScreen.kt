package com.edival.reciostore.presentation.screens.roles

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.roles.components.RolesContent

@Composable
fun RolesScreen(navHostController: NavHostController) {
    Scaffold(topBar = { DefaultTopBar(title = R.string.select_a_role) },
        content = { paddingValues -> RolesContent(navHostController, paddingValues) })
}