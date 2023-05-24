package com.edival.reciostore.presentation.screens.client.address.create.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.components.DefaultTextField
import com.edival.reciostore.presentation.screens.client.address.create.ClientAddressCreateViewModel

@Composable
fun ClientAddressCreateContent(
    padding: PaddingValues, vm: ClientAddressCreateViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    val allModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = dimensionResource(R.dimen.padding_min))
    LaunchedEffect(key1 = vm.errorMessage) {
        vm.showMsg { Toast.makeText(ctx, vm.errorMessage, Toast.LENGTH_SHORT).show() }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen.padding_default))
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(dimensionResource(R.dimen.icon_big_size)),
            imageVector = Icons.Outlined.LocationOn,
            contentDescription = null
        )
        Divider(modifier = allModifier)
        DefaultTextField(
            modifier = allModifier,
            value = vm.state.address,
            onValueChange = { vm.onAddressInput(it) },
            label = R.string.address,
            icon = Icons.Default.LocationOn
        )
        DefaultTextField(
            modifier = allModifier,
            value = vm.state.neighborhood,
            onValueChange = { vm.onNeighborhoodInput(it) },
            label = R.string.neighborhood,
            icon = Icons.Outlined.LocationOn
        )
        DefaultButton(modifier = allModifier,
            text = R.string.new_address,
            enabled = vm.enabledBtn,
            onClick = { vm.validateForm(ctx) { isValid -> if (isValid) vm.createAddress() } })
    }
}