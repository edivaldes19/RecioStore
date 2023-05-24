package com.edival.reciostore.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.User

@Composable
fun UserDetailContent(
    padding: PaddingValues, enabled: Boolean, user: User, onDownload: () -> Unit
) {
    val allModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = dimensionResource(R.dimen.padding_min))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen.padding_default))
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShowImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_default))),
            url = user.img,
            icon = Icons.Outlined.Person
        )
        TextButton(
            modifier = allModifier,
            enabled = enabled,
            onClick = { onDownload() }) { Text(text = stringResource(R.string.download_image)) }
        Divider(modifier = allModifier)
        UserInfoItem(
            modifier = allModifier,
            title = user.id ?: stringResource(R.string.unknown),
            subtitle = R.string.id_src
        )
        UserInfoItem(
            modifier = allModifier,
            title = "${user.name} ${user.surname}",
            subtitle = R.string.username
        )
        UserInfoItem(
            modifier = allModifier,
            title = user.email ?: stringResource(R.string.unknown_email),
            subtitle = R.string.email
        )
        UserInfoItem(
            modifier = allModifier,
            title = user.phone ?: stringResource(R.string.unknown_phone),
            subtitle = R.string.phone
        )
        user.roles?.let { roles ->
            val rolesNames = roles.map { role -> role.name ?: stringResource(R.string.unknown) }
            UserInfoItem(
                modifier = allModifier,
                title = rolesNames.joinToString(),
                subtitle = R.string.roles_src
            )
        }
    }
}