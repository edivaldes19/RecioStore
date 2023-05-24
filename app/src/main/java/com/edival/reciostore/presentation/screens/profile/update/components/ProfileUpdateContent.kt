package com.edival.reciostore.presentation.screens.profile.update.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
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
import com.edival.reciostore.presentation.screens.profile.update.ProfileUpdateViewModel

@Composable
fun ProfileUpdateContent(padding: PaddingValues, vm: ProfileUpdateViewModel = hiltViewModel()) {
    val ctx = LocalContext.current
    val allModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = dimensionResource(R.dimen.padding_min))
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
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
            url = vm.state.imgSelected?.toString() ?: vm.state.img,
            icon = Icons.Outlined.Person
        )
        DefaultTextField(
            modifier = allModifier,
            value = vm.state.name,
            onValueChange = { vm.onNameInput(it) },
            label = R.string.name,
            icon = Icons.Outlined.Create
        )
        DefaultTextField(
            modifier = allModifier,
            value = vm.state.surname,
            onValueChange = { vm.onSurnameInput(it) },
            label = R.string.lastname,
            icon = Icons.Outlined.Info
        )
        DefaultTextField(
            modifier = allModifier,
            value = vm.state.phone,
            onValueChange = { vm.onPhoneInput(it) },
            label = R.string.phone,
            icon = Icons.Outlined.Phone
        )
        DefaultButton(modifier = allModifier,
            text = R.string.update_profile,
            enabled = vm.enabledBtn,
            onClick = { vm.validateForm(ctx) { isValid -> if (isValid) vm.updateUser(ctx) } })
    }
}