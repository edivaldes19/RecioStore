package com.edival.reciostore.presentation.navigation.graph.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.screen.auth.AuthScreen
import com.edival.reciostore.presentation.screens.auth.login.LogInScreen
import com.edival.reciostore.presentation.screens.auth.signup.SignUpScreen
import com.edival.reciostore.presentation.screens.loading.LoadingAppScreen

fun NavGraphBuilder.authNavGraph(navHostController: NavHostController) {
    navigation(route = Graph.AUTH, startDestination = AuthScreen.Loading.route) {
        composable(route = AuthScreen.Loading.route) { LoadingAppScreen(navHostController) }
        composable(route = AuthScreen.LogIn.route) { LogInScreen(navHostController) }
        composable(route = AuthScreen.SignUp.route) { SignUpScreen(navHostController) }
    }
}