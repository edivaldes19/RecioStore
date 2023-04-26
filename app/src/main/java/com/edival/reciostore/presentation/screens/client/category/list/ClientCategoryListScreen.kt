package com.edival.reciostore.presentation.screens.client.category.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.presentation.screens.client.category.list.components.GetCategories

@Composable
fun ClientCategoryListScreen(navHostController: NavHostController) {
    Scaffold { padding -> GetCategories(navHostController, padding) }
}