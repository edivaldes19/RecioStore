package com.edival.reciostore.presentation.screens.admin.category.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
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
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.presentation.navigation.screen.admin.AdminCategoryScreen
import com.edival.reciostore.presentation.screens.admin.category.list.AdminCategoryListViewModel
import com.edival.reciostore.presentation.ui.theme.errorRed
import com.edival.reciostore.presentation.ui.theme.secondaryColor
import com.edival.reciostore.presentation.util.ShowImage

@Composable
fun AdminCategoryListItem(
    navHostController: NavHostController,
    category: Category,
    vm: AdminCategoryListViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier
            .padding(all = dimensionResource(R.dimen.padding_default))
            .clickable {
                navHostController.navigate(AdminCategoryScreen.CategoryUpdate.passCategory(category.toJson()))
            }, shape = RoundedCornerShape(
            topStart = dimensionResource(R.dimen.padding_default),
            topEnd = dimensionResource(R.dimen.padding_default)
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(R.dimen.padding_min))
        ) {
            val (imgCtg, txtName, txtDesc, btnEdit, btnDelete) = createRefs()
            ShowImage(
                modifier = Modifier
                    .height(dimensionResource(R.dimen.icon_small_size))
                    .width(dimensionResource(R.dimen.icon_small_size))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_default)))
                    .constrainAs(imgCtg) {
                        start.linkTo(parent.start)
                        end.linkTo(txtName.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }, url = category.img, icon = Icons.Outlined.Info
            )
            Text(
                modifier = Modifier.constrainAs(txtName) {
                    start.linkTo(imgCtg.end)
                    end.linkTo(btnEdit.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(txtDesc.top)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
                text = category.name ?: stringResource(R.string.unknown),
                style = MaterialTheme.typography.h6
            )
            Text(
                modifier = Modifier.constrainAs(txtDesc) {
                    start.linkTo(imgCtg.end)
                    end.linkTo(btnDelete.start)
                    top.linkTo(txtName.bottom)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
                text = category.description ?: stringResource(R.string.unknown),
                style = MaterialTheme.typography.subtitle1
            )
            IconButton(onClick = {
                navHostController.navigate(AdminCategoryScreen.CategoryUpdate.passCategory(category.toJson()))
            }, modifier = Modifier.constrainAs(btnEdit) {
                start.linkTo(txtName.end)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(btnDelete.top)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null,
                    tint = secondaryColor
                )
            }
            IconButton(onClick = { vm.deleteCategory(category.id) },
                modifier = Modifier.constrainAs(btnDelete) {
                    start.linkTo(txtDesc.end)
                    end.linkTo(parent.end)
                    top.linkTo(btnEdit.bottom)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }) {
                Icon(
                    imageVector = Icons.Outlined.Delete, contentDescription = null, tint = errorRed
                )
            }
        }
    }
}