package com.edival.reciostore.presentation.screens.admin.user.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.ShowImage
import com.edival.reciostore.presentation.screens.admin.user.detail.AdminUserDetailViewModel
import com.edival.reciostore.presentation.screens.profile.info.components.ProfileInfoItem
import com.edival.reciostore.presentation.ui.theme.primaryColor

@Composable
fun AdminUserDetailContent(padding: PaddingValues, vm: AdminUserDetailViewModel = hiltViewModel()) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
            .padding(padding)
    ) {
        val (imgUser, cardInfo) = createRefs()
        val topCard = createGuidelineFromTop(0.33f)
        vm.user?.let { user ->
            ShowImage(
                modifier = Modifier.constrainAs(imgUser) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(topCard)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }, url = user.img, icon = Icons.Outlined.Person
            )
            Card(
                modifier = Modifier.constrainAs(cardInfo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topCard)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }, shape = RoundedCornerShape(
                    topStart = dimensionResource(R.dimen.padding_default),
                    topEnd = dimensionResource(R.dimen.padding_default)
                )
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(all = dimensionResource(R.dimen.padding_default))
                ) {
                    ProfileInfoItem(
                        title = user.id ?: stringResource(R.string.unknown),
                        subtitle = R.string.id_src
                    )
                    ProfileInfoItem(
                        title = "${user.name} ${user.surname}", subtitle = R.string.username
                    )
                    ProfileInfoItem(
                        title = user.email ?: stringResource(R.string.unknown_email),
                        subtitle = R.string.email
                    )
                    ProfileInfoItem(
                        title = user.phone ?: stringResource(R.string.unknown_phone),
                        subtitle = R.string.phone
                    )
                    user.roles?.let { roles ->
                        val rolesNames =
                            roles.map { role -> role.name ?: stringResource(R.string.unknown) }
                        ProfileInfoItem(
                            title = rolesNames.joinToString(), subtitle = R.string.roles_src
                        )
                    }
                }
            }
        }
    }
}