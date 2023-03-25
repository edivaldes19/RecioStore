package com.edival.reciostore.presentation.screens.profile.update

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.profile.update.components.ProfileUpdateContent
import com.edival.reciostore.presentation.screens.profile.update.components.UpdateUserData

@Composable
fun ProfileUpdateScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(
            title = R.string.update_profile,
            upAvailable = true,
            navHostController = navHostController
        )
    }, content = { padding -> ProfileUpdateContent(padding) })
    UpdateUserData()
}