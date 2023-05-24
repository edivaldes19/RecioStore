package com.edival.reciostore.presentation.screens.admin.category.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.MainTopBar
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.screens.admin.category.list.components.DeleteCategory
import com.edival.reciostore.presentation.screens.admin.category.list.components.GetCategories
import kotlinx.coroutines.CoroutineScope

@Composable
fun AdminCategoryListScreen(
    navHostController: NavHostController, scope: CoroutineScope, scaffoldState: ScaffoldState
) {
    Scaffold(scaffoldState = scaffoldState,
        topBar = { MainTopBar(R.string.categories, scope, scaffoldState) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navHostController.navigate(Graph.ADMIN_CATEGORY) }) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
            }
        }) { padding -> GetCategories(navHostController, padding) }
    DeleteCategory()
}