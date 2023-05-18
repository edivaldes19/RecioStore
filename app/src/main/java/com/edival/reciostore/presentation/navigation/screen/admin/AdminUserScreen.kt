package com.edival.reciostore.presentation.navigation.screen.admin

sealed class AdminUserScreen(val route: String) {
    object UserDetail : AdminUserScreen("admin/user/detail/{user}") {
        fun passUser(user: String) = "admin/user/detail/$user"
    }
}