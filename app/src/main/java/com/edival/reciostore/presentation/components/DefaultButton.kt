package com.edival.reciostore.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.edival.reciostore.presentation.ui.theme.primaryColor

@Composable
fun DefaultButton(
    modifier: Modifier,
    @StringRes text: Int,
    onClick: () -> Unit,
    color: Color = primaryColor,
    @DrawableRes icon: Int? = null,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        icon?.let { Icon(painter = painterResource(it), contentDescription = null) }
        Text(text = stringResource(text))
    }
}