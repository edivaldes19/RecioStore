package com.edival.reciostore.presentation.screens.client.address.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
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
        modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_min)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.padding_default))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(R.dimen.padding_min))
        ) {
            val (radioRef, columnInfo, columnBtn) = createRefs()
            val imgGuide = createGuidelineFromStart(0.1f)
            val buttonsGuide = createGuidelineFromEnd(0.1f)
            RadioButton(
                modifier = Modifier.constrainAs(radioRef) {
                    start.linkTo(parent.start)
                    end.linkTo(imgGuide)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
                selected = address.id == vm.selectedAddress,
                onClick = { vm.onSelectedAddressInput(address) },
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
                    text = address.address ?: stringResource(R.string.unknown),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.padding_min),
                        vertical = dimensionResource(R.dimen.padding_ultra_min)
                    ), text = address.neighborhood ?: stringResource(R.string.unknown)
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
                                ShoppingBagScreen.AddressUpdate.passAddress(address.toJson())
                            )
                        },
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null,
                    tint = secondaryColor
                )
                Icon(
                    modifier = Modifier
                        .padding(all = dimensionResource(R.dimen.padding_min))
                        .clickable { vm.deleteAddress(address.id) },
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                    tint = errorRed
                )
            }
        }
    }
}