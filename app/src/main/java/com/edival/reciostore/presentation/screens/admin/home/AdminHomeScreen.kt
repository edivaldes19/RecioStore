package com.edival.reciostore.presentation.screens.admin.home

import android.annotation.SuppressLint
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.edival.reciostore.presentation.navigation.graph.admin.AdminNavGraph
import com.edival.reciostore.presentation.screens.admin.home.components.AdminHomeDrawer

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdminHomeScreen(navHostController: NavHostController = rememberNavController()) {
    val scaffoldState =
        rememberScaffoldState(drawerState = rememberDrawerState(initialValue = DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold(scaffoldState = scaffoldState, drawerContent = {
        AdminHomeDrawer(scope, scaffoldState, navHostController)
    }) { AdminNavGraph(navHostController, scope, scaffoldState) }
}