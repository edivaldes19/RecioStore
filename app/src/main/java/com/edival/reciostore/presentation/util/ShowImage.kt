package com.edival.reciostore.presentation.util

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.edival.reciostore.R

@Composable
fun ShowImage(modifier: Modifier, url: String?, icon: ImageVector) {
    if (url.isNullOrBlank()) {
        Icon(modifier = modifier, imageVector = icon, contentDescription = null, tint = Color.White)
    } else {
        AsyncImage(
            modifier = modifier,
            model = url,
            placeholder = painterResource(R.drawable.outline_downloading),
            error = painterResource(R.drawable.outline_hide_image),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}