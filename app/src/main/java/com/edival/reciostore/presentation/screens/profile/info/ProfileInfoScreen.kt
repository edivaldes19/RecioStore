package com.edival.reciostore.presentation.screens.profile.info

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.presentation.screens.profile.info.components.DeleteAccount
import com.edival.reciostore.presentation.screens.profile.info.components.ProfileContent
import com.edival.reciostore.presentation.screens.profile.info.components.ProfileTopBar

@Composable
fun ProfileInfoScreen(navHostController: NavHostController) {
    Scaffold(topBar = { ProfileTopBar(navHostController) }) { padding -> ProfileContent(padding) }
    DeleteAccount()
}