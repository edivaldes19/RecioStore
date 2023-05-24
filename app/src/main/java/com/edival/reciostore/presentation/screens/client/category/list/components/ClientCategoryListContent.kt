package com.edival.reciostore.presentation.screens.client.category.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.edival.reciostore.domain.model.Category

@Composable
fun ClientCategoryListContent(
    navHostController: NavHostController, padding: PaddingValues, categories: List<Category>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        items(items = categories) { category ->
            ClientCategoryListItem(navHostController, category)
        }
    }
}