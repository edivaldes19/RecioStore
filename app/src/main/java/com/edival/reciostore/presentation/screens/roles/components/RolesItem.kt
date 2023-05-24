package com.edival.reciostore.presentation.screens.roles.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Role
import com.edival.reciostore.presentation.components.ShowImage
import com.edival.reciostore.presentation.navigation.Graph

@Composable
fun RolesItem(navHostController: NavHostController, role: Role) {
    ConstraintLayout(modifier = Modifier.clickable {
        if (!role.route.isNullOrBlank()) {
            navHostController.navigate(role.route) {
                popUpTo(Graph.ROLES) { inclusive = true }
            }
        }
    }) {
        val (imgRole, textRole) = createRefs()
        ShowImage(
            modifier = Modifier
                .height(dimensionResource(R.dimen.icon_big_size))
                .width(dimensionResource(R.dimen.icon_big_size))
                .constrainAs(imgRole) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(textRole.top, margin = 8.dp)
                }, url = role.img, icon = Icons.Outlined.Info
        )
        Text(
            modifier = Modifier.constrainAs(textRole) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(imgRole.bottom, margin = 8.dp)
                bottom.linkTo(parent.bottom)
            },
            text = role.name?.uppercase() ?: stringResource(R.string.unknown),
            style = MaterialTheme.typography.h4
        )
    }
}