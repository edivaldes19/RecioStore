package com.edival.reciostore.presentation.screens.profile.info.components

import android.app.Activity
import android.content.Intent
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.RoleAssignment
import com.edival.reciostore.presentation.MainActivity
import com.edival.reciostore.presentation.navigation.screen.profile.ProfileScreen
import com.edival.reciostore.presentation.screens.profile.info.ProfileInfoViewModel
import com.edival.reciostore.presentation.ui.theme.errorRed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProfileTopBar(
    navHostController: NavHostController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    vm: ProfileInfoViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as? Activity
    val idUser = vm.user?.id
    TopAppBar(title = { Text(text = stringResource(R.string.profile)) }, navigationIcon = {
        IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
            Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
        }
    }, elevation = 0.dp, actions = {
        IconButton(onClick = { vm.showMenu = !vm.showMenu }) {
            Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null)
        }
        DropdownMenu(expanded = vm.showMenu, onDismissRequest = { vm.showMenu = false }) {
            DropdownMenuItem(onClick = { navHostController.navigate(ProfileScreen.ProfileUpdate.route) }) {
                Text(text = stringResource(R.string.update_profile))
            }
            DropdownMenuItem(onClick = {
                activity?.let { act ->
                    act.finish()
                    act.startActivity(Intent(act, MainActivity::class.java))
                }
            }) { Text(text = stringResource(R.string.change_role)) }
            DropdownMenuItem(onClick = {
                if (!idUser.isNullOrBlank()) {
                    navHostController.navigate(
                        ProfileScreen.ProfileRoleFunctions.passRoleAssignment(
                            RoleAssignment(idUser = idUser, toAdmin = true).toJson()
                        )
                    )
                }
            }) { Text(text = stringResource(R.string.i_wanna_admin)) }
            DropdownMenuItem(onClick = {
                if (!idUser.isNullOrBlank()) {
                    navHostController.navigate(
                        ProfileScreen.ProfileRoleFunctions.passRoleAssignment(
                            RoleAssignment(idUser = idUser, toAdmin = false).toJson()
                        )
                    )
                }
            }) { Text(text = stringResource(R.string.i_wanna_client)) }
            DropdownMenuItem(onClick = {
                if (!idUser.isNullOrBlank()) {
                    navHostController.navigate(
                        ProfileScreen.ProfileChangePassword.passIDUser(idUser)
                    )
                }
            }) { Text(text = stringResource(R.string.change_password)) }
            DropdownMenuItem(onClick = {
                activity?.let { act ->
                    vm.logOut()
                    act.finish()
                    act.startActivity(Intent(act, MainActivity::class.java))
                }
            }) { Text(text = stringResource(R.string.logout)) }
            DropdownMenuItem(onClick = {
                if (!idUser.isNullOrBlank() && activity != null) {
                    vm.deleteAccount(idUser)
                    activity.finish()
                    activity.startActivity(Intent(activity, MainActivity::class.java))
                }
            }) { Text(text = stringResource(R.string.delete_account), color = errorRed) }
        }
    })
}