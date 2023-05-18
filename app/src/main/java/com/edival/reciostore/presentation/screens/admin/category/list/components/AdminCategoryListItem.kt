package com.edival.reciostore.presentation.screens.admin.category.list.components

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
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.presentation.components.ShowImage
import com.edival.reciostore.presentation.navigation.screen.admin.AdminCategoryScreen
import com.edival.reciostore.presentation.screens.admin.category.list.AdminCategoryListViewModel
import com.edival.reciostore.presentation.ui.theme.errorRed
import com.edival.reciostore.presentation.ui.theme.secondaryColor

@Composable
fun AdminCategoryListItem(
    navHostController: NavHostController,
    category: Category,
    vm: AdminCategoryListViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier
            .padding(all = dimensionResource(R.dimen.padding_min))
            .clickable {
                navHostController.navigate(
                    AdminCategoryScreen.ProductList.passCategory(category.toJson())
                )
            }, shape = RoundedCornerShape(dimensionResource(R.dimen.padding_default))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(R.dimen.padding_min))
        ) {
            val (imgCtg, columnInfo, columnBtn) = createRefs()
            val imgGuide = createGuidelineFromStart(0.2f)
            val buttonsGuide = createGuidelineFromEnd(0.1f)
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
                    }, url = category.img, icon = Icons.Outlined.Info
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
                    ),
                    text = category.name ?: stringResource(R.string.unknown),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.padding_min),
                        vertical = dimensionResource(R.dimen.padding_ultra_min)
                    ), text = category.description ?: stringResource(R.string.unknown)
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
                                AdminCategoryScreen.CategoryUpdate.passCategory(category.toJson())
                            )
                        },
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null,
                    tint = secondaryColor
                )
                Icon(
                    modifier = Modifier
                        .padding(all = dimensionResource(R.dimen.padding_min))
                        .clickable { vm.deleteCategory(category.id) },
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                    tint = errorRed
                )
            }
        }
    }
}