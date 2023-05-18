package com.edival.reciostore.presentation.screens.profile.info.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import com.edival.reciostore.R

@Composable
fun ProfileInfoItem(title: String, @StringRes subtitle: Int) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (titleRef, subtitleRef, dividerRef) = createRefs()
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.padding_ultra_min))
                .constrainAs(titleRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(subtitleRef.top)
                }, text = title, fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.padding_ultra_min))
                .constrainAs(subtitleRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(titleRef.bottom)
                    bottom.linkTo(dividerRef.top)
                }, text = stringResource(subtitle)
        )
        Divider(modifier = Modifier.constrainAs(dividerRef) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(subtitleRef.bottom)
            bottom.linkTo(parent.bottom)
        })
    }
}