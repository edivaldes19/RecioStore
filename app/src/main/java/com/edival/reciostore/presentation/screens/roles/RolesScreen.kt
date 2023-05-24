package com.edival.reciostore.presentation.screens.roles

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.screens.roles.components.RolesContent

@Composable
fun RolesScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(R.string.select_a_role)) }, elevation = 0.dp)
    }) { padding -> RolesContent(navHostController, padding) }
}