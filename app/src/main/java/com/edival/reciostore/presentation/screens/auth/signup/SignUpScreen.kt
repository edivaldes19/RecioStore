package com.edival.reciostore.presentation.screens.auth.signup

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.auth.signup.components.SignUp
import com.edival.reciostore.presentation.screens.auth.signup.components.SignUpContent

@Composable
fun RegisterScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(
            titleRes = R.string.sign_up, upAvailable = true, navHostController = navHostController
        )
    }) { padding -> SignUpContent(padding = padding) }
    SignUp(navHostController = navHostController)
}