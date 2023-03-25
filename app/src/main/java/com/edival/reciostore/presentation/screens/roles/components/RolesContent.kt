package com.edival.reciostore.presentation.screens.roles.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.presentation.screens.roles.RolesViewModel

@Composable
fun RolesContent(
    navHostController: NavHostController,
    padding: PaddingValues,
    vm: RolesViewModel = hiltViewModel()
) {
    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        vm.authResponse.user?.let { user ->
            items(items = user.roles) { rol -> RolesItem(navHostController, rol) }
        }
    }
}