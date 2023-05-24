package com.edival.reciostore.presentation.screens.auth.login

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.presentation.screens.auth.login.components.CreateToken
import com.edival.reciostore.presentation.screens.auth.login.components.Login
import com.edival.reciostore.presentation.screens.auth.login.components.LoginContent
import com.edival.reciostore.presentation.screens.auth.login.components.UpdateNotificationToken

@Composable
fun LogInScreen(navHostController: NavHostController) {
    Scaffold { padding -> LoginContent(padding, navHostController) }
    Login(navHostController) { data ->
        if (!data.token.isNullOrBlank()) {
            CreateToken { notifyToken ->
                UpdateNotificationToken(data.user?.id, notifyToken, data.token)
            }
        }
    }
}