package com.edival.reciostore.presentation.screens.admin.category.update

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.admin.category.update.components.AdminCategoryUpdateContent
import com.edival.reciostore.presentation.screens.admin.category.update.components.DownloadCtgImg
import com.edival.reciostore.presentation.screens.admin.category.update.components.UpdateCategory

@Composable
fun AdminCategoryUpdateScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(titleRes = R.string.update_category, navHostController = navHostController)
    }) { padding -> AdminCategoryUpdateContent(padding) }
    UpdateCategory(navHostController)
    DownloadCtgImg()
}