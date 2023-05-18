package com.edival.reciostore.presentation.screens.roles

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.roles.components.RolesContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RolesScreen(navHostController: NavHostController) {
    Scaffold(topBar = { DefaultTopBar(titleRes = R.string.select_a_role) }) { padding ->
        RolesContent(navHostController, padding)
//        RolesNavGraph(navHostController)
    }
}