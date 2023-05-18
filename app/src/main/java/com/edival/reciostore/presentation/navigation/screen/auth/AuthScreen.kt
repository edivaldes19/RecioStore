package com.edival.reciostore.presentation.navigation.screen.auth

sealed class AuthScreen(val route: String) {
    object Loading : AuthScreen("loading")
    object LogIn : AuthScreen("login")
    object SignUp : AuthScreen("register")
}