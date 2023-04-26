package com.edival.reciostore.presentation.screens.admin.category.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.screens.admin.category.list.components.DeleteCategory
import com.edival.reciostore.presentation.screens.admin.category.list.components.GetCategories

@Composable
fun AdminCategoryListScreen(navHostController: NavHostController) {
    Scaffold(
        modifier = Modifier.padding(bottom = 55.dp), floatingActionButton = {
            FloatingActionButton(onClick = { navHostController.navigate(Graph.ADMIN_CATEGORY) }) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
            }
        }, isFloatingActionButtonDocked = true, floatingActionButtonPosition = FabPosition.Center
    ) { padding -> GetCategories(navHostController, padding) }
    DeleteCategory()
}