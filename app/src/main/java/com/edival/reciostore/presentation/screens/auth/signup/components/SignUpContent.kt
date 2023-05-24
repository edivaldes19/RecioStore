package com.edival.reciostore.presentation.screens.auth.signup.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.components.DefaultTextField
import com.edival.reciostore.presentation.components.PasswordTextField
import com.edival.reciostore.presentation.screens.auth.signup.SignUpViewModel

@Composable
fun SignUpContent(padding: PaddingValues, vm: SignUpViewModel = hiltViewModel()) {
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
        Text(
            modifier = allModifier,
            text = stringResource(R.string.complete_the_form),
            style = MaterialTheme.typography.h5
        )
        DefaultTextField(
            modifier = allModifier,
            value = vm.state.name,
            onValueChange = { vm.onNameInput(it) },
            label = R.string.name,
            icon = Icons.Default.Person
        )
        DefaultTextField(
            modifier = allModifier,
            value = vm.state.surname,
            onValueChange = { vm.onSurnameInput(it) },
            label = R.string.lastname,
            icon = Icons.Outlined.Person
        )
        DefaultTextField(
            modifier = allModifier,
            value = vm.state.phone,
            onValueChange = { vm.onPhoneInput(it) },
            label = R.string.phone,
            icon = Icons.Outlined.Phone,
            keyboardType = KeyboardType.Number
        )
        DefaultTextField(
            modifier = allModifier,
            value = vm.state.email,
            onValueChange = { vm.onEmailInput(it) },
            label = R.string.email,
            icon = Icons.Outlined.Email,
            keyboardType = KeyboardType.Email
        )
        PasswordTextField(
            modifier = allModifier,
            value = vm.state.password,
            onValueChange = { vm.onPasswordInput(it) },
            label = R.string.password
        )
        PasswordTextField(
            modifier = allModifier,
            value = vm.state.confirmPassword,
            onValueChange = { vm.onConfirmPasswordInput(it) },
            label = R.string.confirm_password
        )
        DefaultButton(modifier = allModifier,
            text = R.string.sign_up,
            onClick = { vm.validateForm(ctx) { isValid -> if (isValid) vm.signUp() } })
    }
}