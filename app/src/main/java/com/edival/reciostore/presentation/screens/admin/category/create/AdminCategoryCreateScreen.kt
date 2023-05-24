package com.edival.reciostore.presentation.screens.admin.category.create

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.admin.category.create.components.AdminCategoryCreateContent
import com.edival.reciostore.presentation.screens.admin.category.create.components.CreateCategory

@Composable
fun AdminCategoryCreateScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(titleRes = R.string.create_category, navHostController = navHostController)
    }, content = { padding -> AdminCategoryCreateContent(padding) })
    CreateCategory(navHostController)
}