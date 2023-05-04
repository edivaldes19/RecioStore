package com.edival.reciostore.presentation.screens.profile.info.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.MainActivity
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.screens.profile.info.ProfileViewModel
import com.edival.reciostore.presentation.ui.theme.errorRed
import com.edival.reciostore.presentation.ui.theme.primaryColor
import com.edival.reciostore.presentation.util.ShowImage

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
            ShowImage(
                modifier = Modifier
                    .height(dimensionResource(R.dimen.icon_big_size))
                    .width(dimensionResource(R.dimen.icon_big_size))
                    .clip(CircleShape)
                    .constrainAs(imgUser) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(cardInfo.top)
                    }, url = user.img, icon = Icons.Outlined.Person
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
                    ProfileInfoItem(
                        title = "${user.name} ${user.surname}", subtitle = R.string.username
                    )
                    ProfileInfoItem(
                        title = user.email ?: stringResource(R.string.unknown_email),
                        subtitle = R.string.email
                    )
                    ProfileInfoItem(
                        title = user.phone ?: stringResource(R.string.unknown_phone),
                        subtitle = R.string.phone
                    )
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.padding_min))
                    ) {
                        val (btnUpdateProfile, btnGoToRoles, btnSignOff) = createRefs()
                        FloatingActionButton(modifier = Modifier.constrainAs(btnUpdateProfile) {
                            start.linkTo(parent.start)
                            end.linkTo(btnGoToRoles.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                            backgroundColor = primaryColor,
                            onClick = { navHostController.navigate(route = "${Graph.PROFILE}/${user.toJson()}") }) {
                            Icon(Icons.Outlined.Edit, null)
                        }
                        FloatingActionButton(modifier = Modifier.constrainAs(btnGoToRoles) {
                            start.linkTo(btnUpdateProfile.end)
                            end.linkTo(btnSignOff.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }, onClick = {
                            activity?.let { act ->
                                act.finish()
                                act.startActivity(Intent(act, MainActivity::class.java))
                            }
                        }) {
                            Icon(Icons.Outlined.Home, null)
                        }
                        FloatingActionButton(modifier = Modifier.constrainAs(btnSignOff) {
                            start.linkTo(btnGoToRoles.end)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }, backgroundColor = errorRed, onClick = {
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