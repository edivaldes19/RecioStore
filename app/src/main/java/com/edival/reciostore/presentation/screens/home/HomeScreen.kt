package com.edival.reciostore.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Scaffold(content = { padding ->
        Text(
            modifier = Modifier.padding(padding), text = "HomeScreen"
        )
    })
}