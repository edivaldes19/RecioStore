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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Rol
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.util.ShowImage

@Composable
fun RolesItem(navHostController: NavHostController, rol: Rol) {
    ConstraintLayout(modifier = Modifier.clickable {
        rol.route?.let { route ->
            navHostController.navigate(route) {
                popUpTo(route = Graph.ROLES) { inclusive = true }
            }
        }
    }) {
        val (imgRol, textRol) = createRefs()
        ShowImage(
            modifier = Modifier
                .height(dimensionResource(R.dimen.icon_big_size))
                .width(dimensionResource(R.dimen.icon_big_size))
                .constrainAs(imgRol) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(textRol.top)
                }, url = rol.img, icon = Icons.Outlined.Info
        )
        Text(
            modifier = Modifier.constrainAs(textRol) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(imgRol.bottom)
                bottom.linkTo(parent.bottom)
            },
            text = rol.name?.uppercase() ?: stringResource(R.string.unknown_name),
            style = MaterialTheme.typography.h4
        )
    }
}