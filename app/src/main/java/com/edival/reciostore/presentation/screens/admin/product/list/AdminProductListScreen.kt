package com.edival.reciostore.presentation.screens.admin.product.list

import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.navigation.screen.admin.AdminCategoryScreen
import com.edival.reciostore.presentation.screens.admin.product.list.components.DeleteProduct
import com.edival.reciostore.presentation.screens.admin.product.list.components.GetProductsByCategoryAdmin

@Composable
fun AdminProductListScreen(navHostController: NavHostController, ctgParam: String) {
    Scaffold(topBar = {
        DefaultTopBar(
            titleStr = Category.fromJson(ctgParam).name,
            upAvailable = true,
            navHostController = navHostController
        )
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            navHostController.navigate(
                AdminCategoryScreen.ProductCreate.passCategory(
                    Category.fromJson(ctgParam).toJson()
                )
            )
        }) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
        }
    }, isFloatingActionButtonDocked = true, floatingActionButtonPosition = FabPosition.Center
    ) { padding -> GetProductsByCategoryAdmin(navHostController, padding) }
    DeleteProduct()
}