package com.edival.reciostore.presentation.screens.profile.role_assignment

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.profile.role_assignment.components.GetInfoRoles
import com.edival.reciostore.presentation.screens.profile.role_assignment.components.UpdateUserRoles

@Composable
fun ProfileRoleAssignmentScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(titleRes = R.string.role_assignment, navHostController = navHostController)
    }) { padding -> GetInfoRoles(padding) }
    UpdateUserRoles(navHostController)
}