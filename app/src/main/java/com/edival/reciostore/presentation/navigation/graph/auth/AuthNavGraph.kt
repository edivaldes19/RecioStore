package com.edival.reciostore.presentation.navigation.graph.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.screen.auth.AuthScreen
import com.edival.reciostore.presentation.screens.auth.login.LoginScreen
import com.edival.reciostore.presentation.screens.auth.signup.RegisterScreen

fun NavGraphBuilder.authNavGraph(navHostController: NavHostController) {
    navigation(route = Graph.AUTH, startDestination = AuthScreen.Login.route) {
        composable(route = AuthScreen.Login.route) { LoginScreen(navHostController) }
        composable(route = AuthScreen.Register.route) { RegisterScreen(navHostController) }
    }
}