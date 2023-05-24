package com.edival.reciostore.presentation.screens.admin.category.create.components

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.core.Config
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.components.DefaultTextField
import com.edival.reciostore.presentation.components.ShowImage
import com.edival.reciostore.presentation.screens.admin.category.create.AdminCategoryCreateViewModel

@Composable
fun AdminCategoryCreateContent(
    padding: PaddingValues, vm: AdminCategoryCreateViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    val allModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = dimensionResource(R.dimen.padding_min))
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { photo -> vm.onImageInput(photo) }
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
        ShowImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_default)))
                .clickable { launcher.launch(Config.IMAGES_MT) },
            url = vm.state.imgSelected?.toString(),
            icon = Icons.Outlined.AddCircle
        )
        Divider(modifier = allModifier)
        DefaultTextField(
            modifier = allModifier,
            value = vm.state.name,
            onValueChange = { vm.onNameInput(it) },
            label = R.string.category_name,
            icon = Icons.Outlined.Create
        )
        DefaultTextField(
            modifier = allModifier,
            value = vm.state.description,
            onValueChange = { vm.onDescriptionInput(it) },
            label = R.string.description,
            icon = Icons.Outlined.Info
        )
        DefaultButton(modifier = allModifier,
            text = R.string.create_category,
            enabled = vm.enabledBtn,
            onClick = { vm.validateForm(ctx) { isValid -> if (isValid) vm.createCategory(ctx) } })
    }
}