package com.edival.reciostore.presentation.screens.client.order.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Order
import com.edival.reciostore.presentation.components.OrderDetailInfoItem
import com.edival.reciostore.presentation.components.OrderDetailProductItem
import com.edival.reciostore.presentation.screens.client.order.detail.ClientOrderDetailViewModel

@Composable
fun ClientOrderDetailContent(
    padding: PaddingValues, order: Order, vm: ClientOrderDetailViewModel = hiltViewModel()
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        val (productsListRef, cardInfo) = createRefs()
        val topCard = createGuidelineFromTop(0.5f)
        LazyColumn(modifier = Modifier.constrainAs(productsListRef) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(topCard)
            height = Dimension.fillToConstraints
            width = Dimension.fillToConstraints
        }) {
            items(items = order.ohp ?: listOf()) { orderHasProducts ->
                OrderDetailProductItem(orderHasProducts)
            }
        }
        Card(
            modifier = Modifier.constrainAs(cardInfo) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(topCard)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }, shape = RoundedCornerShape(
                topStart = dimensionResource(R.dimen.padding_default),
                topEnd = dimensionResource(R.dimen.padding_default)
            )
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(all = dimensionResource(R.dimen.padding_default))
            ) {
                OrderDetailInfoItem(
                    titleRes = R.string.order_number_src,
                    subtitle = order.id,
                    icon = R.drawable.outline_numbers
                )
                OrderDetailInfoItem(
                    titleRes = R.string.order_date_src,
                    subtitle = order.created_at,
                    icon = R.drawable.outline_date_range
                )
                order.user?.let { client ->
                    OrderDetailInfoItem(
                        titleRes = R.string.customer_and_phone_number,
                        subtitle = "${client.name} ${client.surname} - ${client.phone}",
                        icon = R.drawable.outline_person
                    )
                }
                order.address?.let { address ->
                    OrderDetailInfoItem(
                        titleRes = R.string.delivery_address_src,
                        subtitle = "${address.address} - ${address.neighborhood}",
                        icon = R.drawable.outline_location_on
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    text = stringResource(R.string.quantity_value, vm.totalToPay)
                )
            }
        }
    }
}