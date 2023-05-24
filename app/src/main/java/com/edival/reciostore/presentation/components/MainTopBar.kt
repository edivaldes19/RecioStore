package com.edival.reciostore.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainTopBar(@StringRes titleRes: Int, scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(title = { Text(text = stringResource(titleRes)) }, navigationIcon = {
        IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
            Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
        }
    }, elevation = 0.dp)
}