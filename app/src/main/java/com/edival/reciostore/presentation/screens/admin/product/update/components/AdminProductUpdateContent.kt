package com.edival.reciostore.presentation.screens.admin.product.update.components

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.core.Config
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.components.DefaultTextField
import com.edival.reciostore.presentation.components.ShowImage
import com.edival.reciostore.presentation.screens.admin.product.update.AdminProductUpdateViewModel
import com.edival.reciostore.presentation.ui.theme.primaryColor

@Composable
fun AdminProductUpdateContent(
    padding: PaddingValues, vm: AdminProductUpdateViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    val launcher1 =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { photo ->
                vm.imgUri1 = photo
                vm.onImage1Input(photo.toString())
            }
        }
    val launcher2 =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { photo ->
                vm.imgUri2 = photo
                vm.onImage2Input(photo.toString())
            }
        }
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
        val (iconProduct1, iconProduct2, cardInfo) = createRefs()
        ShowImage(modifier = Modifier
            .height(dimensionResource(R.dimen.icon_big_size))
            .width(dimensionResource(R.dimen.icon_big_size))
            .clip(CircleShape)
            .clickable { launcher1.launch(Config.IMAGES_MT) }
            .constrainAs(iconProduct1) {
                start.linkTo(parent.start)
                end.linkTo(iconProduct2.start)
                top.linkTo(parent.top, margin = 16.dp)
                bottom.linkTo(cardInfo.top, margin = 8.dp)
            }, url = vm.state.img1, icon = Icons.Outlined.Add
        )
        ShowImage(modifier = Modifier
            .height(dimensionResource(R.dimen.icon_big_size))
            .width(dimensionResource(R.dimen.icon_big_size))
            .clip(CircleShape)
            .clickable { launcher2.launch(Config.IMAGES_MT) }
            .constrainAs(iconProduct2) {
                start.linkTo(iconProduct1.end)
                end.linkTo(parent.end)
                top.linkTo(parent.top, margin = 16.dp)
                bottom.linkTo(cardInfo.top, margin = 8.dp)
            }, url = vm.state.img2, icon = Icons.Outlined.Add
        )
        Card(
            modifier = Modifier.constrainAs(cardInfo) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(iconProduct1.bottom, margin = 8.dp)
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
                    keyboardType = KeyboardType.Number
                )
                DefaultButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.padding_min)),
                    text = R.string.update_product,
                    enabled = vm.enabledBtn,
                    onClick = { vm.validateForm(ctx) { isValid -> if (isValid) vm.updateProduct() } })
            }
        }
    }
}