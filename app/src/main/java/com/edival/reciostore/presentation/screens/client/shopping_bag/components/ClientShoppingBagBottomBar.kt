package com.edival.reciostore.presentation.screens.client.shopping_bag.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.navigation.screen.client.ShoppingBagScreen
import com.edival.reciostore.presentation.screens.client.shopping_bag.ClientShoppingBagViewModel

@Composable
fun ClientShoppingBagBottomBar(
    navHostController: NavHostController, vm: ClientShoppingBagViewModel = hiltViewModel()
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(R.dimen.padding_min))
    ) {
        val (txtTotalSrc, txtTotalValue, btnConfirm) = createRefs()
        Text(
            modifier = Modifier.constrainAs(txtTotalSrc) {
                start.linkTo(parent.start)
                end.linkTo(btnConfirm.start)
                top.linkTo(parent.top)
                bottom.linkTo(txtTotalValue.top)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }, text = stringResource(R.string.total), overflow = TextOverflow.Ellipsis, maxLines = 1
        )
        Text(
            modifier = Modifier.constrainAs(txtTotalValue) {
                start.linkTo(parent.start)
                end.linkTo(btnConfirm.start)
                top.linkTo(txtTotalSrc.bottom)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }, text = "$${vm.total}", overflow = TextOverflow.Ellipsis, maxLines = 1
        )
        DefaultButton(modifier = Modifier.constrainAs(btnConfirm) {
            start.linkTo(txtTotalValue.end)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        },
            text = R.string.confirm_order,
            onClick = { navHostController.navigate(ShoppingBagScreen.AddressList.route) })
    }
}