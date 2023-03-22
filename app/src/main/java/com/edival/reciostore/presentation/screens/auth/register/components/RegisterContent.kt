package com.edival.reciostore.presentation.screens.auth.register.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.components.DefaultTextField
import com.edival.reciostore.presentation.screens.auth.register.RegisterViewModel
import com.edival.reciostore.presentation.ui.theme.primaryColor

@Composable
fun RegisterContent(padding: PaddingValues, vm: RegisterViewModel = hiltViewModel()) {
    val ctx = LocalContext.current
    LaunchedEffect(key1 = vm.errorMessage) {
        if (vm.errorMessage.isNotBlank()) {
            Toast.makeText(ctx, vm.errorMessage, Toast.LENGTH_SHORT).show()
            vm.errorMessage = ""
        }
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
            .padding(padding)
    ) {
        val (iconUser, textImg, cardForm) = createRefs()
        Icon(
            modifier = Modifier
                .height(dimensionResource(R.dimen.icon_big_size))
                .width(dimensionResource(R.dimen.icon_big_size))
                .constrainAs(iconUser) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(textImg.top)
                },
            tint = Color.White,
            imageVector = Icons.Outlined.Person,
            contentDescription = null
        )
        Text(
            modifier = Modifier.constrainAs(textImg) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(iconUser.bottom)
                bottom.linkTo(cardForm.top, margin = 8.dp)
            },
            text = stringResource(R.string.select_an_image),
            style = MaterialTheme.typography.h5,
            color = Color.White
        )
        Card(
            modifier = Modifier.constrainAs(cardForm) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(textImg.bottom, margin = 8.dp)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }, shape = RoundedCornerShape(
                topStart = dimensionResource(R.dimen.padding_default),
                topEnd = dimensionResource(R.dimen.padding_default)
            )
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(all = dimensionResource(R.dimen.padding_default))
            ) {
                Text(
                    text = stringResource(R.string.complete_the_form),
                    style = MaterialTheme.typography.h5
                )
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    value = vm.state.name,
                    onValueChange = { vm.onNameInput(it) },
                    label = R.string.name,
                    icon = Icons.Default.Person
                )
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    value = vm.state.surname,
                    onValueChange = { vm.onSurnameInput(it) },
                    label = R.string.lastname,
                    icon = Icons.Outlined.Person
                )
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    value = vm.state.phone,
                    onValueChange = { vm.onPhoneInput(it) },
                    label = R.string.phone,
                    icon = Icons.Outlined.Phone,
                    keyboardType = KeyboardType.Number
                )
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    value = vm.state.email,
                    onValueChange = { vm.onEmailInput(it) },
                    label = R.string.email,
                    icon = Icons.Outlined.Email,
                    keyboardType = KeyboardType.Email
                )
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    value = vm.state.password,
                    onValueChange = { vm.onPasswordInput(it) },
                    label = R.string.password,
                    icon = Icons.Default.Lock,
                    keyboardType = KeyboardType.Password,
                    hideText = true
                )
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    value = vm.state.confirmPassword,
                    onValueChange = { vm.onConfirmPasswordInput(it) },
                    label = R.string.confirm_password,
                    icon = Icons.Outlined.Lock,
                    keyboardType = KeyboardType.Password,
                    hideText = true
                )
                DefaultButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.padding_min)),
                    text = R.string.sign_up,
                    onClick = { vm.validateForm(ctx) { isValid -> if (isValid) vm.signUp() } })
            }
        }
    }
}