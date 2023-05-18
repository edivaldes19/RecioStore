package com.edival.reciostore.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.edival.reciostore.R

@Composable
fun PasswordTextField(
    modifier: Modifier, value: String, onValueChange: (value: String) -> Unit, @StringRes label: Int
) {
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { if (it.length <= 50) onValueChange(it) },
        label = { Text(text = stringResource(label)) },
        leadingIcon = { Icon(imageVector = Icons.Outlined.Lock, contentDescription = null) },
        trailingIcon = {
            val icon =
                if (passwordVisible) R.drawable.outline_visibility_off else R.drawable.outline_visibility
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painter = painterResource(id = icon), contentDescription = null)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
        ),
        singleLine = true,
        visualTransformation = if (passwordVisible) PasswordVisualTransformation() else VisualTransformation.None
    )
}