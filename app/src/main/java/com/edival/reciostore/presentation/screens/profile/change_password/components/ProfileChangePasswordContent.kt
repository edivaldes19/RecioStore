package com.edival.reciostore.presentation.screens.profile.change_password.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.components.PasswordTextField
import com.edival.reciostore.presentation.screens.profile.change_password.ProfileChangePasswordViewModel

@Composable
fun ProfileChangePasswordContent(
    padding: PaddingValues, vm: ProfileChangePasswordViewModel = hiltViewModel()
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
            .padding(all = dimensionResource(R.dimen.padding_default))
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = allModifier,
            text = stringResource(R.string.complete_the_form),
            style = MaterialTheme.typography.h6
        )
        PasswordTextField(
            modifier = allModifier,
            value = vm.state.oldPassword,
            onValueChange = { vm.onOldPasswordInput(it) },
            label = R.string.password
        )
        PasswordTextField(
            modifier = allModifier,
            value = vm.state.newPassword,
            onValueChange = { vm.onNewPasswordInput(it) },
            label = R.string.new_password
        )
        DefaultButton(modifier = allModifier,
            text = R.string.change_password,
            enabled = vm.enabledBtn,
            onClick = { vm.validateForm(ctx) { isValid -> if (isValid) vm.updatePassword() } })
    }
}