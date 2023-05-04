package com.edival.reciostore.presentation.screens.client.address.list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Address
import com.edival.reciostore.presentation.navigation.screen.client.ShoppingBagScreen
import com.edival.reciostore.presentation.screens.client.address.list.ClientAddressListViewModel
import com.edival.reciostore.presentation.ui.theme.errorRed
import com.edival.reciostore.presentation.ui.theme.secondaryColor

@Composable
fun ClientAddressListItem(
    navHostController: NavHostController,
    address: Address,
    vm: ClientAddressListViewModel = hiltViewModel()
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
            val (btnRadio, txtAddress, txtNeigh, btnEdit, btnDelete) = createRefs()
            val halfGuide = createGuidelineFromTop(0.5f)
            RadioButton(
                modifier = Modifier.constrainAs(btnRadio) {
                    start.linkTo(parent.start)
                    end.linkTo(txtAddress.start, margin = 4.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
                selected = address.id == vm.selectedAddress,
                onClick = { vm.onSelectedAddressInput(address) },
            )
            Text(
                modifier = Modifier.constrainAs(txtAddress) {
                    start.linkTo(btnRadio.end, margin = 4.dp)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(halfGuide)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
                text = address.address ?: stringResource(R.string.unknown_address),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.constrainAs(txtNeigh) {
                    start.linkTo(btnRadio.end, margin = 4.dp)
                    end.linkTo(parent.end)
                    top.linkTo(halfGuide)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }, text = address.neighborhood ?: stringResource(R.string.unknown_neighborhood)
            )
            IconButton(onClick = {
                navHostController.navigate(ShoppingBagScreen.AddressUpdate.passAddress(address.toJson()))
            }, modifier = Modifier.constrainAs(btnEdit) {
                start.linkTo(txtAddress.end, margin = 4.dp)
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
            IconButton(onClick = { vm.deleteAddress(address.id) },
                modifier = Modifier.constrainAs(btnDelete) {
                    start.linkTo(txtNeigh.end, margin = 4.dp)
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