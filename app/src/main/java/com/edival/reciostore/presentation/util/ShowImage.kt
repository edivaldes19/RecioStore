package com.edival.reciostore.presentation.util

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun ShowImage(modifier: Modifier, url: String?, icon: ImageVector) {
    if (url.isNullOrBlank()) {
        Icon(modifier = modifier, imageVector = icon, contentDescription = null, tint = Color.White)
    } else {
        AsyncImage(
            modifier = modifier,
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}