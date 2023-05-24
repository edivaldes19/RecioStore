package com.edival.reciostore.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun UserInfoItem(modifier: Modifier, title: String, @StringRes subtitle: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(modifier = modifier, text = title, fontWeight = FontWeight.Bold)
        Text(modifier = modifier, text = stringResource(subtitle))
        Divider(modifier = modifier)
    }
}