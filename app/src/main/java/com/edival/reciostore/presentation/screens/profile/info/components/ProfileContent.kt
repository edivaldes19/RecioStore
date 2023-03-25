package com.edival.reciostore.presentation.screens.profile.info.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.edival.reciostore.R
import com.edival.reciostore.presentation.MainActivity
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.screens.profile.info.ProfileViewModel
import com.edival.reciostore.presentation.ui.theme.primaryColor

@Composable
fun ProfileContent(
    padding: PaddingValues,
    navHostController: NavHostController,
    vm: ProfileViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as? Activity
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
            .padding(padding)
    ) {
        val (imgUser, cardInfo) = createRefs()
        val topCard = createGuidelineFromTop(0.5f)
        vm.user?.let { user ->
            if (user.img.isNullOrBlank()) {
                Icon(
                    modifier = Modifier
                        .height(dimensionResource(R.dimen.icon_big_size))
                        .width(dimensionResource(R.dimen.icon_big_size))
                        .clip(CircleShape)
                        .constrainAs(imgUser) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(cardInfo.top)
                        },
                    imageVector = Icons.Outlined.Person,
                    contentDescription = stringResource(R.string.profile_picture)
                )
            } else {
                AsyncImage(
                    modifier = Modifier
                        .height(dimensionResource(R.dimen.icon_big_size))
                        .width(dimensionResource(R.dimen.icon_big_size))
                        .clip(CircleShape)
                        .constrainAs(imgUser) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(cardInfo.top)
                        },
                    model = user.img,
                    contentDescription = stringResource(R.string.profile_picture),
                    contentScale = ContentScale.Crop
                )
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
                    ProfileInfoItem(
                        icon = Icons.Outlined.Person,
                        title = "${user.name} ${user.surname}",
                        subtitle = R.string.username
                    )
                    ProfileInfoItem(
                        icon = Icons.Outlined.Email,
                        title = user.email.orEmpty(),
                        subtitle = R.string.email
                    )
                    ProfileInfoItem(
                        icon = Icons.Outlined.Phone, title = user.phone, subtitle = R.string.phone
                    )
                    ConstraintLayout(
                        modifier = Modifier
                            .padding(vertical = dimensionResource(R.dimen.padding_min))
                            .fillMaxWidth()
                    ) {
                        val (btnUpdateProfile, btnSignOff) = createRefs()
                        FloatingActionButton(modifier = Modifier.constrainAs(btnUpdateProfile) {
                            start.linkTo(parent.start)
                            end.linkTo(btnSignOff.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                            backgroundColor = primaryColor,
                            onClick = { navHostController.navigate(route = "${Graph.PROFILE}/${user.toJson()}") }) {
                            Icon(Icons.Outlined.Edit, null)
                        }
                        FloatingActionButton(modifier = Modifier.constrainAs(btnSignOff) {
                            start.linkTo(btnUpdateProfile.end)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }, onClick = {
                            activity?.let { act ->
                                vm.logOut()
                                act.finish()
                                act.startActivity(Intent(act, MainActivity::class.java))
                            }
                        }) { Icon(Icons.Outlined.ExitToApp, null) }
                    }
                }
            }
        }
    }
}