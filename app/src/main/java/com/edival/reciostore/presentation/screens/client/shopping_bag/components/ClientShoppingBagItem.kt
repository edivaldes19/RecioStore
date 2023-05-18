package com.edival.reciostore.presentation.screens.client.shopping_bag.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.ShoppingBagProduct
import com.edival.reciostore.presentation.components.ShowImage
import com.edival.reciostore.presentation.screens.client.shopping_bag.ClientShoppingBagViewModel
import com.edival.reciostore.presentation.ui.theme.errorRed

@Composable
fun ClientShoppingBagItem(
    product: ShoppingBagProduct, vm: ClientShoppingBagViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_min)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.padding_default))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(R.dimen.padding_min))
        ) {
            val (imgCtg, columnInfo, columnBtn) = createRefs()
            val imgGuide = createGuidelineFromStart(0.2f)
            val buttonsGuide = createGuidelineFromEnd(0.2f)
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
                    }, url = product.img, icon = Icons.Outlined.Info
            )
            Column(modifier = Modifier.constrainAs(columnInfo) {
                start.linkTo(imgGuide)
                end.linkTo(buttonsGuide)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }) {
                Text(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.padding_min),
                        vertical = dimensionResource(R.dimen.padding_ultra_min)
                    ), text = product.name, fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.padding_min),
                        vertical = dimensionResource(R.dimen.padding_ultra_min)
                    )
                ) {
                    Icon(
                        modifier = Modifier
                            .weight(1f)
                            .clickable(enabled = vm.enabledBtnSub) { vm.subItem(product) },
                        painter = painterResource(R.drawable.outline_horizontal_rule),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "${product.quantity}",
                        textAlign = TextAlign.Center
                    )
                    Icon(
                        modifier = Modifier
                            .weight(1f)
                            .clickable(enabled = vm.enabledBtnAdd) { vm.addItem(product) },
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null
                    )
                }
            }
            Column(modifier = Modifier.constrainAs(columnBtn) {
                start.linkTo(buttonsGuide)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }) {
                Text(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.padding_min),
                        vertical = dimensionResource(R.dimen.padding_ultra_min)
                    ), text = "$${product.price * product.quantity}"
                )
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(
                            horizontal = dimensionResource(R.dimen.padding_min),
                            vertical = dimensionResource(R.dimen.padding_ultra_min)
                        )
                        .clickable { vm.deleteItem(product.id) },
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                    tint = errorRed
                )
            }
        }
    }
}