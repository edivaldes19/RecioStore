package com.edival.reciostore.presentation.navigation.screen.profile

sealed class ProfileScreen(val route: String) {
    object ProfileUpdate : ProfileScreen("profile/update")
    object ProfileRoleFunctions : ProfileScreen("profile/role_assignment/{role_assignment}") {
        fun passRoleAssignment(roleAssignment: String): String {
            return "profile/role_assignment/$roleAssignment"
        }
    }

    object ProfileChangePassword : ProfileScreen("profile/change_password/{id_user}") {
        fun passIDUser(idUser: String): String = "profile/change_password/$idUser"
    }
}