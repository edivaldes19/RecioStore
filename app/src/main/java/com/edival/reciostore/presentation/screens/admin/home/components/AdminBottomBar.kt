package com.edival.reciostore.presentation.screens.admin.home.components

import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.edival.reciostore.presentation.navigation.screen.admin.AdminScreen

@Composable
fun AdminBottomBar(navHostController: NavHostController) {
    val screens = listOf(AdminScreen.CategoryList, AdminScreen.OrderList, AdminScreen.Profile)
    val navBackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackEntry?.destination
    val bottomBarDestination = screens.any { screen -> screen.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation {
            screens.forEach { screen ->
                AdminBottomBarItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navHostController = navHostController
                )
            }
        }
    }
}