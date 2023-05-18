package com.edival.reciostore.presentation.screens.client.home.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.edival.reciostore.presentation.navigation.screen.client.ClientScreen

@Composable
fun RowScope.ClientBottomBarItem(
    screen: ClientScreen, currentDestination: NavDestination?, navHostController: NavHostController
) {
    BottomNavigationItem(label = { Text(text = stringResource(screen.title)) },
        icon = {
            Icon(
                painter = painterResource(screen.icon),
                contentDescription = stringResource(screen.title)
            )
        },
        selected = currentDestination?.hierarchy?.any { destination -> destination.route == screen.route } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navHostController.navigate(route = screen.route) {
                popUpTo(navHostController.graph.findStartDestination().id)
            }
        })
}