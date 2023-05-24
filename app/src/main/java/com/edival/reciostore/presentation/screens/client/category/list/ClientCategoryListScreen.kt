package com.edival.reciostore.presentation.screens.client.category.list

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.MainTopBar
import com.edival.reciostore.presentation.screens.client.category.list.components.GetCategories
import kotlinx.coroutines.CoroutineScope

@Composable
fun ClientCategoryListScreen(
    navHostController: NavHostController, scope: CoroutineScope, scaffoldState: ScaffoldState
) {
    Scaffold(scaffoldState = scaffoldState,
        topBar = { MainTopBar(R.string.categories, scope, scaffoldState) }) { padding ->
        GetCategories(navHostController, padding)
    }
}