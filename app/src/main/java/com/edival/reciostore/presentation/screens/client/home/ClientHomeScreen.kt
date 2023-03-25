package com.edival.reciostore.presentation.screens.client.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.edival.reciostore.presentation.navigation.graph.client.ClientNavGraph
import com.edival.reciostore.presentation.screens.client.home.components.ClientBottomBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClientHomeScreen(navHostController: NavHostController = rememberNavController()) {
    Scaffold(bottomBar = { ClientBottomBar(navHostController = navHostController) }) {
        ClientNavGraph(navHostController = navHostController)
    }
}