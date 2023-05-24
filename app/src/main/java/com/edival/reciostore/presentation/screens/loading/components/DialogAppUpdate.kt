package com.edival.reciostore.presentation.screens.loading.components

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.edival.reciostore.R

@Composable
fun DialogAppUpdate(activity: Activity?, padding: PaddingValues, appVersion: String) {
    AlertDialog(onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { activity?.finishAndRemoveTask() }) { Text(text = stringResource(R.string.ok_msg)) }
        },
        modifier = Modifier.padding(padding),
        title = { Text(text = stringResource(R.string.update_required)) },
        text = { Text(text = stringResource(R.string.dialog_text_loading, appVersion)) })
}