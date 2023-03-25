package com.edival.reciostore.presentation.screens.roles.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Rol
import com.edival.reciostore.presentation.navigation.Graph

@Composable
fun RolesItem(navHostController: NavHostController, rol: Rol) {
    ConstraintLayout(modifier = Modifier.clickable {
        navHostController.navigate(route = rol.route) {
            popUpTo(route = Graph.ROLES) { inclusive = true }
        }
    }) {
        val (imgRol, textRol) = createRefs()
        AsyncImage(
            modifier = Modifier
                .height(dimensionResource(R.dimen.icon_big_size))
                .width(dimensionResource(R.dimen.icon_big_size))
                .constrainAs(imgRol) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(textRol.top)
                }, model = rol.img, contentDescription = stringResource(R.string.rol)
        )
        Text(modifier = Modifier.constrainAs(textRol) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(imgRol.bottom)
            bottom.linkTo(parent.bottom)
        }, text = rol.name.uppercase(), style = MaterialTheme.typography.h4)
    }
}