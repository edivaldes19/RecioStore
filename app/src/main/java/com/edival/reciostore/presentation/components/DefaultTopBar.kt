package com.edival.reciostore.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.ui.theme.primaryColor

@Composable
fun DefaultTopBar(
    @StringRes titleRes: Int? = null,
    titleStr: String? = null,
    upAvailable: Boolean = false,
    navHostController: NavHostController? = null
) {
    TopAppBar(title = {
        when {
            titleRes != null && titleStr == null -> Text(text = stringResource(titleRes))
            titleRes == null && titleStr != null -> Text(text = titleStr)
            else -> Text(text = stringResource(R.string.unknown))
        }
    }, backgroundColor = primaryColor, elevation = 0.dp, navigationIcon = {
        if (upAvailable) {
            IconButton(onClick = { navHostController?.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    })
}