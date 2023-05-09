package com.edival.reciostore.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.navigation.screen.client.ShoppingBagScreen

@Composable
fun DefaultSearchView(
    value: String,
    onValueChange: (value: String) -> Unit,
    navHostController: NavHostController,
    onClick: () -> Unit
) {
    TopAppBar(title = {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = { text -> onValueChange(text) },
            label = { Text(text = stringResource(R.string.look_for)) },
            trailingIcon = {
                IconButton(onClick = { onClick() }) {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                }
            })
    }, actions = {
        IconButton(onClick = { navHostController.navigate(ShoppingBagScreen.ShoppingBag.route) }) {
            Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = null)
        }
    })
}