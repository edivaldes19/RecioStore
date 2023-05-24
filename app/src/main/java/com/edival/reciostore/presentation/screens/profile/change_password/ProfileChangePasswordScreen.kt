package com.edival.reciostore.presentation.screens.profile.change_password

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.profile.change_password.components.ProfileChangePasswordContent
import com.edival.reciostore.presentation.screens.profile.change_password.components.UpdatePassword

@Composable
fun ProfileChangePasswordScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(titleRes = R.string.change_password, navHostController = navHostController)
    }) { padding -> ProfileChangePasswordContent(padding) }
    UpdatePassword(navHostController)
}