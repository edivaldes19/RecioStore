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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
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
    LaunchedEffect(key1 = vm.errorMessage) {
        if (vm.errorMessage.isNotBlank()) {
            Toast.makeText(ctx, vm.errorMessage, Toast.LENGTH_SHORT).show()
            vm.errorMessage = ""
        }
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
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.padding_min)),
            value = vm.state.oldPassword,
            onValueChange = { vm.onOldPasswordInput(it) },
            label = R.string.password
        )
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.padding_min)),
            value = vm.state.newPassword,
            onValueChange = { vm.onNewPasswordInput(it) },
            label = R.string.new_password
        )
        DefaultButton(modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.padding_min)),
            text = R.string.change_password,
            enabled = vm.enabledBtn,
            onClick = { vm.validateForm(ctx) { isValid -> if (isValid) vm.updatePassword() } })
    }
}