package com.edival.reciostore.presentation.screens.profile.info

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.presentation.screens.profile.info.components.ProfileContent

@Composable
fun ProfileScreen(navHostController: NavHostController) {
    Scaffold { padding -> ProfileContent(padding, navHostController) }
}