package com.edival.reciostore.presentation.screens.admin.user.list

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.MainTopBar
import com.edival.reciostore.presentation.screens.admin.user.list.components.GetUsers
import kotlinx.coroutines.CoroutineScope

@Composable
fun AdminUserListScreen(
    navHostController: NavHostController, scope: CoroutineScope, scaffoldState: ScaffoldState
) {
    Scaffold(scaffoldState = scaffoldState,
        topBar = { MainTopBar(R.string.users_src, scope, scaffoldState) }) { padding ->
        GetUsers(navHostController, padding)
    }
}