package com.edival.reciostore.presentation.screens.admin.product.create.components

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.AdminProductImgItem
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.components.DefaultTextField
import com.edival.reciostore.presentation.screens.admin.product.create.AdminProductCreateViewModel

@Composable
fun AdminProductCreateContent(
    padding: PaddingValues, vm: AdminProductCreateViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    val allModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = dimensionResource(R.dimen.padding_min))
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { photo -> vm.addImg(photo) }
        }
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
        LazyRow(
            modifier = allModifier, horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.padding_min), Alignment.CenterHorizontally
            ), verticalAlignment = Alignment.CenterVertically
        ) {
            items(vm.uriList) { uri -> AdminProductImgItem(uri) { vm.deleteImg(uri) } }
            item {
                FloatingActionButton(onClick = { vm.pickImage(ctx, launcher) }) {
                    Icon(
                        painter = painterResource(R.drawable.outline_add_photo),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
        Divider(modifier = allModifier)
        Text(
            modifier = allModifier, text = stringResource(
                R.string.category_value, vm.categoryName ?: stringResource(R.string.unknown)
            ), fontWeight = FontWeight.Bold
        )
        DefaultTextField(
            modifier = allModifier,
            value = vm.state.name,
            onValueChange = { vm.onNameInput(it) },
            label = R.string.product_name,
            icon = Icons.Outlined.Create
        )
        DefaultTextField(
            modifier = allModifier,
            value = vm.state.description,
            onValueChange = { vm.onDescriptionInput(it) },
            label = R.string.description,
            icon = Icons.Outlined.Info
        )
        DefaultTextField(
            modifier = allModifier,
            value = "${vm.state.price}",
            onValueChange = { vm.onPriceInput(it) },
            label = R.string.price,
            icon = Icons.Outlined.Star,
            keyboardType = KeyboardType.Number
        )
        DefaultButton(modifier = allModifier,
            text = R.string.create_product,
            enabled = vm.enabledBtn,
            onClick = { vm.validateForm(ctx) { isValid -> if (isValid) vm.createProduct(ctx) } })
    }
}