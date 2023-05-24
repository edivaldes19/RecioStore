package com.edival.reciostore.presentation.screens.client.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.ShowImage
import com.edival.reciostore.presentation.navigation.screen.client.ClientScreen
import com.edival.reciostore.presentation.screens.publicViewModels.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ClientHomeDrawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navHostController: NavHostController,
    vm: HomeViewModel = hiltViewModel()
) {
    val items = listOf(
        ClientScreen.CategoryList,
        ClientScreen.ProductList,
        ClientScreen.OrderList,
        ClientScreen.Profile,
        ClientScreen.ShoppingBag
    )
    val navBackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackEntry?.destination?.route
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen.padding_default))
    ) {
        vm.user?.let { user ->
            ShowImage(
                modifier = Modifier
                    .height(dimensionResource(R.dimen.icon_big_size))
                    .width(dimensionResource(R.dimen.icon_big_size))
                    .clip(CircleShape)
                    .padding(vertical = dimensionResource(R.dimen.padding_min)),
                url = user.img,
                icon = Icons.Outlined.Person
            )
            Text(
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_min)),
                text = "${user.name} ${user.surname}",
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_min)),
                text = user.email ?: stringResource(R.string.unknown_email)
            )
            Divider(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_min)))
            Text(
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_min)),
                text = stringResource(R.string.client),
                style = MaterialTheme.typography.caption
            )
            Divider(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_min)))
        }
        items.forEach { item ->
            Row(modifier = Modifier
                .clickable {
                    navHostController.navigate(item.route) { launchSingleTop = true }
                    scope.launch { scaffoldState.drawerState.close() }
                }
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.padding_ultra_min))
                .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_min)))
                .background(if (currentRoute == item.route) Color.LightGray else Color.Transparent)
                .padding(dimensionResource(R.dimen.padding_default)),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    painter = painterResource(item.icon),
                    contentDescription = null
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = stringResource(item.title))
            }
        }
    }
}