package com.edival.reciostore.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.edival.reciostore.R

@Composable
fun DialogCapturePicture(
    status: MutableState<Boolean>, takePhoto: () -> Unit, pickImage: () -> Unit
) {
    if (status.value) {
        AlertDialog(modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.icon_big_size)),
            onDismissRequest = { status.value = false },
            title = { Text(text = stringResource(R.string.select_an_option)) },
            buttons = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(all = dimensionResource(R.dimen.padding_default))
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_min)),
                        onClick = { pickImage() }) { Text(text = stringResource(R.string.gallery)) }
                    Button(modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_min)),
                        onClick = { takePhoto() }) { Text(text = stringResource(R.string.camera)) }
                }
            })
    }
}