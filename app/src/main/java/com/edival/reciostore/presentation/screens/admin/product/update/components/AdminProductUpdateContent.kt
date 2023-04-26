package com.edival.reciostore.presentation.screens.admin.product.update.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.components.DefaultTextField
import com.edival.reciostore.presentation.components.DialogCapturePicture
import com.edival.reciostore.presentation.screens.admin.product.update.AdminProductUpdateViewModel
import com.edival.reciostore.presentation.ui.theme.primaryColor
import com.edival.reciostore.presentation.util.ShowImage

@Composable
fun AdminProductUpdateContent(
    padding: PaddingValues, vm: AdminProductUpdateViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    val stateDialog = remember { mutableStateOf(false) }
    val stateImageNumber = remember { mutableStateOf(1) }
    vm.resultingActivityHandler.Handle()
    LaunchedEffect(key1 = vm.errorMessage) {
        if (vm.errorMessage.isNotBlank()) {
            Toast.makeText(ctx, vm.errorMessage, Toast.LENGTH_SHORT).show()
            vm.errorMessage = ""
        }
    }
    DialogCapturePicture(status = stateDialog,
        takePhoto = { vm.takePhoto(ctx, stateImageNumber.value) },
        pickImage = { vm.pickImage(ctx, stateImageNumber.value) })
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
            .padding(padding)
    ) {
        val (imgProduct1, imgProduct2, btnUpdatePhotos, cardInfo) = createRefs()
        val topCard = createGuidelineFromTop(0.4f)
        ShowImage(modifier = Modifier
            .height(dimensionResource(R.dimen.icon_big_size))
            .width(dimensionResource(R.dimen.icon_big_size))
            .clip(CircleShape)
            .clickable {
                stateDialog.value = true
                stateImageNumber.value = 1
            }
            .constrainAs(imgProduct1) {
                start.linkTo(parent.start)
                end.linkTo(imgProduct2.start)
                top.linkTo(parent.top)
                bottom.linkTo(btnUpdatePhotos.top)
            }, url = vm.state.img1, icon = Icons.Outlined.Add
        )
        ShowImage(modifier = Modifier
            .height(dimensionResource(R.dimen.icon_big_size))
            .width(dimensionResource(R.dimen.icon_big_size))
            .clip(CircleShape)
            .clickable {
                stateDialog.value = true
                stateImageNumber.value = 2
            }
            .constrainAs(imgProduct2) {
                start.linkTo(imgProduct1.end)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(btnUpdatePhotos.top)
            }, url = vm.state.img2, icon = Icons.Outlined.Add
        )
        FloatingActionButton(modifier = Modifier.constrainAs(btnUpdatePhotos) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(imgProduct1.bottom)
            bottom.linkTo(cardInfo.top)
        }, onClick = { vm.updateProductImages() }) {
            Icon(Icons.Outlined.Edit, null)
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
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    value = vm.state.name,
                    onValueChange = { vm.onNameInput(it) },
                    label = R.string.product_name,
                    icon = Icons.Outlined.Create
                )
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    value = vm.state.description,
                    onValueChange = { vm.onDescriptionInput(it) },
                    label = R.string.description,
                    icon = Icons.Outlined.Info
                )
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    value = "${vm.state.price}",
                    onValueChange = { vm.onPriceInput(it) },
                    label = R.string.price,
                    icon = Icons.Outlined.Face,
                    keyboardType = KeyboardType.Phone
                )
                DefaultButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.padding_min)),
                    text = R.string.update_product,
                    onClick = { vm.validateForm(ctx) { isValid -> if (isValid) vm.updateProduct() } })
            }
        }
    }
}