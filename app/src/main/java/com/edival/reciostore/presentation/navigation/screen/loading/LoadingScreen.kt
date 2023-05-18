package com.edival.reciostore.presentation.navigation.screen.loading

sealed class LoadingScreen(val route: String) {
    object Loading : LoadingScreen("loading")
}