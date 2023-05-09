package com.edival.reciostore.presentation.screens.auth.login

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.presentation.screens.auth.login.components.Login
import com.edival.reciostore.presentation.screens.auth.login.components.LoginContent

@Composable
fun LoginScreen(navHostController: NavHostController) {
    Scaffold { padding -> LoginContent(padding, navHostController) }
    Login(navHostController)
}