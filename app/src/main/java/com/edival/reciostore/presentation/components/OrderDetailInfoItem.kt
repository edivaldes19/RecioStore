package com.edival.reciostore.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.edival.reciostore.R

@Composable
fun OrderDetailInfoItem(@StringRes titleRes: Int, subtitle: String?, @DrawableRes icon: Int) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (titleRef, subtitleRef, iconRef, dividerRef) = createRefs()
        val iconGuide = createGuidelineFromEnd(0.1f)
        Text(
            modifier = Modifier
                .padding(vertical = dimensionResource(R.dimen.padding_ultra_min))
                .constrainAs(titleRef) {
                    start.linkTo(parent.start)
                    end.linkTo(iconGuide)
                    top.linkTo(parent.top)
                    bottom.linkTo(subtitleRef.top)
                    width = Dimension.fillToConstraints
                }, text = stringResource(titleRes)
        )
        Text(
            modifier = Modifier
                .padding(vertical = dimensionResource(R.dimen.padding_ultra_min))
                .constrainAs(subtitleRef) {
                    start.linkTo(parent.start)
                    end.linkTo(iconGuide)
                    top.linkTo(titleRef.bottom)
                    bottom.linkTo(dividerRef.top)
                    width = Dimension.fillToConstraints
                }, text = subtitle ?: stringResource(R.string.unknown), fontWeight = FontWeight.Bold
        )
        Icon(modifier = Modifier.constrainAs(iconRef) {
            start.linkTo(iconGuide)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }, painter = painterResource(icon), contentDescription = null)
        Divider(modifier = Modifier.constrainAs(dividerRef) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(subtitleRef.bottom)
            bottom.linkTo(parent.bottom)
        })
    }
}