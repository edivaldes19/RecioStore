package com.edival.reciostore.presentation.screens.client.product.listByCategory.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.presentation.screens.client.product.listByCategory.ClientProductListByCategoryViewModel
import com.edival.reciostore.presentation.util.ShowImage

@Composable
fun ClientProductListByCategoryItem(
    navHostController: NavHostController,
    product: Product,
    vm: ClientProductListByCategoryViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_default)),
        shape = RoundedCornerShape(
            topStart = dimensionResource(R.dimen.padding_default),
            topEnd = dimensionResource(R.dimen.padding_default)
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(R.dimen.padding_min))
        ) {
            val (imgProduct, txtName, txtDesc, txtPrice, btnEdit, btnDelete) = createRefs()
            ShowImage(
                modifier = Modifier
                    .height(dimensionResource(R.dimen.icon_small_size))
                    .width(dimensionResource(R.dimen.icon_small_size))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_default)))
                    .constrainAs(imgProduct) {
                        start.linkTo(parent.start)
                        end.linkTo(txtName.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }, url = product.img1, icon = Icons.Outlined.Info
            )
            Text(
                modifier = Modifier.constrainAs(txtPrice) {
                    start.linkTo(parent.start)
                    end.linkTo(txtDesc.start)
                    top.linkTo(imgProduct.bottom)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }, text = "$${product.price}", style = MaterialTheme.typography.subtitle1
            )
            Text(
                modifier = Modifier.constrainAs(txtName) {
                    start.linkTo(imgProduct.end)
                    end.linkTo(btnEdit.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(txtDesc.top)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
                text = product.name ?: stringResource(R.string.unknown_name),
                style = MaterialTheme.typography.h6
            )
            Text(
                modifier = Modifier.constrainAs(txtDesc) {
                    start.linkTo(txtPrice.end)
                    end.linkTo(btnDelete.start)
                    top.linkTo(txtName.bottom)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
                text = product.description ?: stringResource(R.string.unknown_description),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}