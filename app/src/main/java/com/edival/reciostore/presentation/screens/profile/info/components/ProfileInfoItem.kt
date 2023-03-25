package com.edival.reciostore.presentation.screens.profile.info.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.edival.reciostore.R
import com.edival.reciostore.presentation.ui.theme.primaryColor

@Composable
fun ProfileInfoItem(icon: ImageVector, title: String, @StringRes subtitle: Int) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (iconRef, titleRef, subtitleRef) = createRefs()
        Icon(
            modifier = Modifier
                .padding(end = dimensionResource(R.dimen.padding_min))
                .constrainAs(iconRef) {
                    start.linkTo(parent.start)
                    end.linkTo(titleRef.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            tint = primaryColor,
            imageVector = icon,
            contentDescription = stringResource(subtitle)
        )
        Text(
            modifier = Modifier
                .padding(vertical = dimensionResource(R.dimen.padding_min))
                .constrainAs(titleRef) {
                    start.linkTo(iconRef.end)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(subtitleRef.top)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }, text = title, style = MaterialTheme.typography.h6
        )
        Text(
            modifier = Modifier
                .padding(vertical = dimensionResource(R.dimen.padding_min))
                .constrainAs(subtitleRef) {
                    start.linkTo(iconRef.end)
                    end.linkTo(parent.end)
                    top.linkTo(titleRef.bottom)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }, text = stringResource(subtitle), style = MaterialTheme.typography.subtitle1
        )
    }
}