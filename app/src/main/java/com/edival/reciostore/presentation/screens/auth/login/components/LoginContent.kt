package com.edival.reciostore.presentation.screens.auth.login.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.components.DefaultTextField
import com.edival.reciostore.presentation.components.PasswordTextField
import com.edival.reciostore.presentation.navigation.screen.auth.AuthScreen
import com.edival.reciostore.presentation.screens.auth.login.LoginViewModel
import com.edival.reciostore.presentation.ui.theme.primaryColor
import com.edival.reciostore.presentation.ui.theme.primaryLightColor

@Composable
fun LoginContent(
    padding: PaddingValues,
    navHostController: NavHostController,
    vm: LoginViewModel = hiltViewModel()
) {
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
        val (iconApp, textApp, cardForm) = createRefs()
        val topIcon = createGuidelineFromTop(0.2f)
        val topCard = createGuidelineFromTop(0.6f)
        Icon(
            modifier = Modifier
                .height(dimensionResource(R.dimen.icon_big_size))
                .width(dimensionResource(R.dimen.icon_big_size))
                .constrainAs(iconApp) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topIcon)
                    bottom.linkTo(textApp.top)
                },
            tint = Color.White,
            painter = painterResource(R.drawable.outline_store),
            contentDescription = stringResource(R.string.app_icon)
        )
        Text(
            modifier = Modifier.constrainAs(textApp) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(iconApp.bottom)
            },
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h3,
            color = Color.White
        )
        Card(
            modifier = Modifier.constrainAs(cardForm) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(topCard)
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
                Text(text = stringResource(R.string.login), style = MaterialTheme.typography.h5)
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
                PasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    value = vm.state.password,
                    onValueChange = { vm.onPasswordInput(it) },
                    label = R.string.password
                )
                DefaultButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.padding_min)),
                    text = R.string.login,
                    onClick = { vm.validateForm(ctx) { isValid -> if (isValid) vm.logIn() } })
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(R.dimen.padding_min)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_ultra_min)),
                        text = stringResource(R.string.not_have_account),
                    )
                    Text(modifier = Modifier
                        .clickable { navHostController.navigate(AuthScreen.SignUp.route) }
                        .padding(all = dimensionResource(R.dimen.padding_ultra_min)),
                        text = stringResource(R.string.sign_up_here),
                        color = primaryLightColor,
                        fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}