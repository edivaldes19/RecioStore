package com.edival.reciostore.presentation.screens.admin.category.create.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.components.DefaultTextField
import com.edival.reciostore.presentation.components.DialogCapturePicture
import com.edival.reciostore.presentation.screens.admin.category.create.AdminCategoryCreateViewModel
import com.edival.reciostore.presentation.ui.theme.primaryColor
import com.edival.reciostore.presentation.util.ShowImage

@Composable
fun AdminCategoryCreateContent(
    padding: PaddingValues, vm: AdminCategoryCreateViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    val stateDialog = remember { mutableStateOf(false) }
    vm.resultingActivityHandler.Handle()
    LaunchedEffect(key1 = vm.errorMessage) {
        if (vm.errorMessage.isNotBlank()) {
            Toast.makeText(ctx, vm.errorMessage, Toast.LENGTH_SHORT).show()
            vm.errorMessage = ""
        }
    }
    DialogCapturePicture(status = stateDialog,
        takePhoto = { vm.takePhoto(ctx) },
        pickImage = { vm.pickImage(ctx) })
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
            .padding(padding)
    ) {
        val (iconCategory, cardInfo) = createRefs()
        val topCard = createGuidelineFromTop(0.2f)
        ShowImage(modifier = Modifier
            .height(dimensionResource(R.dimen.icon_big_size))
            .width(dimensionResource(R.dimen.icon_big_size))
            .clip(CircleShape)
            .clickable { stateDialog.value = true }
            .constrainAs(iconCategory) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(cardInfo.top)
            }, url = vm.state.img, icon = Icons.Outlined.Person
        )
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
                    label = R.string.category_name,
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
                DefaultButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.padding_min)),
                    text = R.string.create_category,
                    onClick = { vm.validateForm(ctx) { isValid -> if (isValid) vm.createCategory() } })
            }
        }
    }
}