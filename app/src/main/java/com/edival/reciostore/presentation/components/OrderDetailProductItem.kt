package com.edival.reciostore.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.OrderHasProducts

@Composable
fun OrderDetailProductItem(ohp: OrderHasProducts) {
    Card(
        modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_min)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.padding_default))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(R.dimen.padding_min))
        ) {
            val (imgCtg, columnInfo) = createRefs()
            val imgGuide = createGuidelineFromStart(0.2f)
            ohp.product?.phi?.let { images ->
                ShowImage(
                    modifier = Modifier
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_default)))
                        .constrainAs(imgCtg) {
                            start.linkTo(parent.start)
                            end.linkTo(imgGuide)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }, url = images.first().img_url, icon = Icons.Outlined.Info
                )
            }
            Column(modifier = Modifier.constrainAs(columnInfo) {
                start.linkTo(imgGuide)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }) {
                Text(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.padding_min),
                        vertical = dimensionResource(R.dimen.padding_ultra_min)
                    ),
                    text = ohp.product?.name ?: stringResource(R.string.unknown),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.padding_min),
                        vertical = dimensionResource(R.dimen.padding_ultra_min)
                    ), text = stringResource(R.string.quantity_value, ohp.quantity)
                )
            }
        }
    }
}