package com.edival.reciostore.presentation.screens.roles.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RolesContent(paddingValues: PaddingValues) {
    Text(modifier = Modifier.padding(paddingValues), text = "RolesScreen")
}