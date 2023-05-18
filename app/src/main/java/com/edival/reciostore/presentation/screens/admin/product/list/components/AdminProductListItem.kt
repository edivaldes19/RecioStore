package com.edival.reciostore.presentation.screens.admin.product.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.presentation.components.ShowImage
import com.edival.reciostore.presentation.navigation.screen.admin.AdminCategoryScreen
import com.edival.reciostore.presentation.screens.admin.product.list.AdminProductListViewModel
import com.edival.reciostore.presentation.ui.theme.errorRed
import com.edival.reciostore.presentation.ui.theme.secondaryColor

@Composable
fun AdminProductListItem(
    navHostController: NavHostController,
    product: Product,
    vm: AdminProductListViewModel = hiltViewModel()
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
            val buttonsGuide = createGuidelineFromEnd(0.1f)
            product.phi?.let { images ->
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
                end.linkTo(buttonsGuide)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }) {
                Text(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.padding_min),
                        vertical = dimensionResource(R.dimen.padding_ultra_min)
                    ),
                    text = product.name ?: stringResource(R.string.unknown),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.padding_min),
                        vertical = dimensionResource(R.dimen.padding_ultra_min)
                    ), text = product.description ?: stringResource(R.string.unknown)
                )
            }
            Column(modifier = Modifier.constrainAs(columnBtn) {
                start.linkTo(buttonsGuide)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }) {
                Icon(
                    modifier = Modifier
                        .padding(all = dimensionResource(R.dimen.padding_min))
                        .clickable {
                            navHostController.navigate(
                                AdminCategoryScreen.ProductUpdate.passProduct(product.toJson())
                            )
                        },
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null,
                    tint = secondaryColor
                )
                Icon(
                    modifier = Modifier
                        .padding(all = dimensionResource(R.dimen.padding_min))
                        .clickable { vm.deleteProduct(product.id) },
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                    tint = errorRed
                )
            }
        }
    }
}