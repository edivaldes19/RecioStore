package com.edival.reciostore.presentation.components

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.edival.reciostore.R
import com.edival.reciostore.presentation.ui.theme.errorRed

@Composable
fun AdminProductImgItem(uri: Uri, onClickDelete: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        ShowImage(
            modifier = Modifier
                .size(dimensionResource(R.dimen.icon_medium_size))
                .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_default))),
            url = uri.toString(),
            icon = Icons.Default.AddCircle
        )
        IconButton(onClick = onClickDelete) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = null, tint = errorRed)
        }
    }
}