package com.edival.reciostore.presentation.screens.client.product.detail.components

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.components.DotsIndicator
import com.edival.reciostore.presentation.components.SliderView
import com.edival.reciostore.presentation.screens.client.product.detail.ClientProductDetailViewModel
import com.edival.reciostore.presentation.ui.theme.backgroundGray
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClientProductDetailContent(
    padding: PaddingValues, vm: ClientProductDetailViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    val state = rememberPagerState()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        vm.product?.let { prod ->
            val images =
                prod.phi?.map { productHasImages -> productHasImages.img_url.orEmpty() } ?: listOf()
            val (cntSlider, cardInfo, btnSub, txtQuantity, btnAdd, btnSubmit) = createRefs()
            val bottomCntSlider = createGuidelineFromTop(0.4f)
            val bottomCard = createGuidelineFromTop(0.9f)
            val topCard = createGuidelineFromTop(0.375f)
            ConstraintLayout(modifier = Modifier
                .padding(padding)
                .constrainAs(cntSlider) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(bottomCntSlider)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }) {
                val (imageSlider, dotsIndicator) = createRefs()
                SliderView(
                    modifier = Modifier
                        .background(backgroundGray)
                        .constrainAs(imageSlider) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(dotsIndicator.top)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }, state = state, images = images
                )
                DotsIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .constrainAs(dotsIndicator) {
                            top.linkTo(imageSlider.bottom)
                            bottom.linkTo(parent.bottom)
                        }, totalDots = images.size, selectedIndex = state.currentPage
                )
            }
            LaunchedEffect(key1 = state.currentPage) {
                delay(10000)
                var newPos = state.currentPage + 1
                if (newPos > images.lastIndex) newPos = 0
                state.animateScrollToPage(newPos)
            }
            Card(
                modifier = Modifier.constrainAs(cardInfo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topCard)
                    bottom.linkTo(bottomCard)
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.padding_min)),
                        text = prod.name ?: stringResource(R.string.unknown_name),
                        style = MaterialTheme.typography.h6
                    )
                    Divider()
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.padding_min)),
                        text = stringResource(R.string.description),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.padding_min)),
                        text = prod.description ?: stringResource(R.string.unknown_description)
                    )
                    Divider()
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.padding_min)),
                        text = stringResource(R.string.price),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.padding_min)),
                        text = "${prod.price}"
                    )
                    Divider()
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.padding_min)),
                        text = stringResource(R.string.your_order),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.padding_min)),
                        text = stringResource(R.string.quantity_value, vm.quantity)
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.padding_min)),
                        text = stringResource(R.string.price_cu_value, vm.totalPrice)
                    )
                }
            }
            IconButton(modifier = Modifier.constrainAs(btnSub) {
                start.linkTo(parent.start)
                end.linkTo(txtQuantity.start)
                top.linkTo(bottomCard)
                bottom.linkTo(parent.bottom)
            }, onClick = { vm.subItem() }, enabled = vm.enabledBtnSub) {
                Icon(painterResource(R.drawable.outline_horizontal_rule), contentDescription = null)
            }
            Text(modifier = Modifier.constrainAs(txtQuantity) {
                start.linkTo(btnSub.end)
                end.linkTo(btnAdd.start)
                top.linkTo(bottomCard)
                bottom.linkTo(parent.bottom)
            }, text = "${vm.quantity}")
            IconButton(modifier = Modifier.constrainAs(btnAdd) {
                start.linkTo(txtQuantity.end)
                end.linkTo(btnSubmit.start)
                top.linkTo(bottomCard)
                bottom.linkTo(parent.bottom)
            }, onClick = { vm.addItem() }, enabled = vm.enabledBtnAdd) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
            }
            DefaultButton(modifier = Modifier.constrainAs(btnSubmit) {
                start.linkTo(btnAdd.end)
                end.linkTo(parent.end)
                top.linkTo(bottomCard)
                bottom.linkTo(parent.bottom)
            }, text = R.string.add, onClick = {
                vm.saveProductInBag {
                    Toast.makeText(ctx, R.string.product_to_sb, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}