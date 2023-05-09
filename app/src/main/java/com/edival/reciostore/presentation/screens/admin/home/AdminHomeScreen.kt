package com.edival.reciostore.presentation.screens.admin.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.edival.reciostore.presentation.navigation.graph.admin.AdminNavGraph
import com.edival.reciostore.presentation.screens.admin.home.components.AdminBottomBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdminHomeScreen(navHostController: NavHostController = rememberNavController()) {
    Scaffold(bottomBar = { AdminBottomBar(navHostController) }) {
        AdminNavGraph(navHostController)
    }
}