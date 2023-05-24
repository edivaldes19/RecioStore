package com.edival.reciostore.presentation.navigation.graph.admin

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.graph.profile.profileNavGraph
import com.edival.reciostore.presentation.navigation.screen.admin.AdminScreen
import com.edival.reciostore.presentation.screens.admin.category.list.AdminCategoryListScreen
import com.edival.reciostore.presentation.screens.admin.order.list.AdminOrderListScreen
import com.edival.reciostore.presentation.screens.admin.user.list.AdminUserListScreen
import com.edival.reciostore.presentation.screens.profile.info.ProfileInfoScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun AdminNavGraph(
    navHostController: NavHostController, scope: CoroutineScope, scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navHostController,
        route = Graph.ADMIN,
        startDestination = AdminScreen.CategoryList.route
    ) {
        composable(AdminScreen.CategoryList.route) {
            AdminCategoryListScreen(navHostController, scope, scaffoldState)
        }
        composable(AdminScreen.OrderList.route) {
            AdminOrderListScreen(navHostController, scope, scaffoldState)
        }
        composable(AdminScreen.UserList.route) {
            AdminUserListScreen(navHostController, scope, scaffoldState)
        }
        composable(AdminScreen.Profile.route) {
            ProfileInfoScreen(navHostController, scope, scaffoldState)
        }
        adminCategoryNavGraph(navHostController)
        adminOrderNavGraph(navHostController)
        adminUserNavGraph(navHostController)
        profileNavGraph(navHostController)
    }
}