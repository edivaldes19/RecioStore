package com.edival.reciostore.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Order

@Composable
fun OrderListItem(order: Order, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(all = dimensionResource(R.dimen.padding_min))
            .clickable { onClick() },
        shape = RoundedCornerShape(dimensionResource(R.dimen.padding_default))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.padding_min),
                    vertical = dimensionResource(R.dimen.padding_ultra_min)
                ), text = stringResource(
                    R.string.order_value, order.id ?: stringResource(R.string.unknown)
                ), fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis, maxLines = 1
            )
            Text(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.padding_min),
                    vertical = dimensionResource(R.dimen.padding_ultra_min)
                ), text = stringResource(
                    R.string.date_value, order.created_at ?: stringResource(R.string.unknown)
                ), overflow = TextOverflow.Ellipsis, maxLines = 1
            )
            Text(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.padding_min),
                    vertical = dimensionResource(R.dimen.padding_ultra_min)
                ), text = stringResource(
                    R.string.client_value, "${order.user?.name} ${order.user?.surname}"
                ), overflow = TextOverflow.Ellipsis, maxLines = 1
            )
            Text(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.padding_min),
                    vertical = dimensionResource(R.dimen.padding_ultra_min)
                ), text = stringResource(
                    R.string.deliver_to_value,
                    order.address?.address ?: stringResource(R.string.unknown)
                ), overflow = TextOverflow.Ellipsis, maxLines = 1
            )
            Text(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.padding_min),
                    vertical = dimensionResource(R.dimen.padding_ultra_min)
                ), text = stringResource(
                    R.string.status_value, order.status ?: stringResource(R.string.unknown)
                ), overflow = TextOverflow.Ellipsis, maxLines = 1
            )
        }
    }
}