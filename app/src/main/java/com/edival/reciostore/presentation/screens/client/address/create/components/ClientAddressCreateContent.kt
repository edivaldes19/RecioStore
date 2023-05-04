package com.edival.reciostore.presentation.screens.client.address.create.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.components.DefaultTextField
import com.edival.reciostore.presentation.screens.client.address.create.ClientAddressCreateViewModel
import com.edival.reciostore.presentation.ui.theme.primaryColor

@Composable
fun ClientAddressCreateContent(
    padding: PaddingValues, vm: ClientAddressCreateViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    LaunchedEffect(key1 = vm.errorMessage) {
        if (vm.errorMessage.isNotBlank()) {
            Toast.makeText(ctx, vm.errorMessage, Toast.LENGTH_SHORT).show()
            vm.errorMessage = ""
        }
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
            .padding(padding)
    ) {
        val (iconAddress, cardForm) = createRefs()
        val halfGuide = createGuidelineFromTop(0.5f)
        Icon(
            modifier = Modifier
                .height(dimensionResource(R.dimen.icon_big_size))
                .width(dimensionResource(R.dimen.icon_big_size))
                .constrainAs(iconAddress) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(halfGuide)
                },
            tint = Color.White,
            imageVector = Icons.Outlined.LocationOn,
            contentDescription = null
        )
        Card(
            modifier = Modifier.constrainAs(cardForm) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(halfGuide)
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
                Text(
                    text = stringResource(R.string.complete_the_form),
                    style = MaterialTheme.typography.h5
                )
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    value = vm.state.address,
                    onValueChange = { vm.onAddressInput(it) },
                    label = R.string.address,
                    icon = Icons.Outlined.LocationOn
                )
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    value = vm.state.neighborhood,
                    onValueChange = { vm.onNeighborhoodInput(it) },
                    label = R.string.neighborhood,
                    icon = Icons.Outlined.Place
                )
                DefaultButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.padding_min)),
                    text = R.string.new_address,
                    onClick = { vm.validateForm(ctx) { isValid -> if (isValid) vm.createAddress() } })
            }
        }
    }
}