package com.edival.reciostore.presentation.screens.admin.product.list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.presentation.navigation.screen.admin.AdminCategoryScreen
import com.edival.reciostore.presentation.screens.admin.product.list.AdminProductListViewModel
import com.edival.reciostore.presentation.ui.theme.errorRed
import com.edival.reciostore.presentation.ui.theme.secondaryColor
import com.edival.reciostore.presentation.util.ShowImage

@Composable
fun AdminProductListItem(
    navHostController: NavHostController,
    product: Product,
    vm: AdminProductListViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier.padding(
            horizontal = dimensionResource(R.dimen.padding_default),
            vertical = dimensionResource(R.dimen.padding_ultra_min)
        ), shape = RoundedCornerShape(dimensionResource(R.dimen.padding_default))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(R.dimen.padding_min))
        ) {
            val (imgCtg, txtName, txtDesc, btnEdit, btnDelete) = createRefs()
            val halfGuide = createGuidelineFromTop(0.5f)
            val imgGuide = createGuidelineFromStart(0.25f)
            ShowImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_default)))
                    .constrainAs(imgCtg) {
                        start.linkTo(parent.start)
                        end.linkTo(imgGuide, margin = 4.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }, url = product.img1, icon = Icons.Outlined.Info
            )
            Text(
                modifier = Modifier.constrainAs(txtName) {
                    start.linkTo(imgGuide, margin = 4.dp)
                    end.linkTo(btnEdit.start, margin = 4.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(halfGuide)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
                text = product.name ?: stringResource(R.string.unknown),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.constrainAs(txtDesc) {
                    start.linkTo(imgGuide, margin = 4.dp)
                    end.linkTo(btnDelete.start, margin = 4.dp)
                    top.linkTo(halfGuide)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }, text = product.description ?: stringResource(R.string.unknown)
            )
            IconButton(onClick = {
                navHostController.navigate(AdminCategoryScreen.ProductUpdate.passProduct(product.toJson()))
            }, modifier = Modifier.constrainAs(btnEdit) {
                start.linkTo(txtName.end, margin = 4.dp)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(halfGuide)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null,
                    tint = secondaryColor
                )
            }
            IconButton(onClick = { vm.deleteProduct(product.id) },
                modifier = Modifier.constrainAs(btnDelete) {
                    start.linkTo(txtDesc.end, margin = 4.dp)
                    end.linkTo(parent.end)
                    top.linkTo(halfGuide)
                    bottom.linkTo(parent.bottom)
                }) {
                Icon(
                    imageVector = Icons.Outlined.Delete, contentDescription = null, tint = errorRed
                )
            }
        }
    }
}