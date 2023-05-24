package com.edival.reciostore.presentation.screens.client.home

import android.annotation.SuppressLint
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.edival.reciostore.presentation.navigation.graph.client.ClientNavGraph
import com.edival.reciostore.presentation.screens.client.home.components.ClientHomeDrawer

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClientHomeScreen(navHostController: NavHostController = rememberNavController()) {
    val scaffoldState =
        rememberScaffoldState(drawerState = rememberDrawerState(initialValue = DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold(scaffoldState = scaffoldState, drawerContent = {
        ClientHomeDrawer(scope, scaffoldState, navHostController)
    }) { ClientNavGraph(navHostController, scope, scaffoldState) }
}