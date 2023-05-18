package com.edival.reciostore.presentation.screens.admin.user.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.RoleID
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.presentation.components.ShowImage
import com.edival.reciostore.presentation.navigation.screen.admin.AdminUserScreen

@Composable
fun AdminUserListItem(navHostController: NavHostController, user: User) {
    Card(
        modifier = Modifier
            .padding(all = dimensionResource(R.dimen.padding_min))
            .clickable {
                navHostController.navigate(AdminUserScreen.UserDetail.passUser(user.toJson()))
            }, shape = RoundedCornerShape(dimensionResource(R.dimen.padding_default))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(R.dimen.padding_min))
        ) {
            val (imgUser, columnInfo, columnIcons) = createRefs()
            val imgGuide = createGuidelineFromStart(0.2f)
            val iconsGuide = createGuidelineFromEnd(0.1f)
            ShowImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_default)))
                    .constrainAs(imgUser) {
                        start.linkTo(parent.start)
                        end.linkTo(imgGuide)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }, url = user.img, icon = Icons.Outlined.Info
            )
            Column(modifier = Modifier.constrainAs(columnInfo) {
                start.linkTo(imgGuide)
                end.linkTo(iconsGuide)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }) {
                Text(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.padding_min),
                        vertical = dimensionResource(R.dimen.padding_ultra_min)
                    ), text = "${user.name} ${user.surname}", fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.padding_min),
                        vertical = dimensionResource(R.dimen.padding_ultra_min)
                    ), text = user.phone ?: stringResource(R.string.unknown)
                )
            }
            Column(modifier = Modifier.constrainAs(columnIcons) {
                start.linkTo(iconsGuide)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }) {
                user.roles?.let { roles ->
                    val rolesIds = roles.map { role -> role.id }
                    when {
                        rolesIds.contains(RoleID.ADMIN.id) && rolesIds.contains(RoleID.CLIENT.id) -> {
                            Icon(
                                modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_min)),
                                painter = painterResource(R.drawable.admin_color),
                                contentDescription = null
                            )
                            Icon(
                                modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_min)),
                                painter = painterResource(R.drawable.client_color),
                                contentDescription = null
                            )
                        }

                        rolesIds.contains(RoleID.ADMIN.id) -> {
                            Icon(
                                modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_min)),
                                painter = painterResource(R.drawable.admin_color),
                                contentDescription = null
                            )
                        }

                        rolesIds.contains(RoleID.CLIENT.id) -> {
                            Icon(
                                modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_min)),
                                painter = painterResource(R.drawable.client_color),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}