package com.edival.reciostore.presentation.screens.profile.info

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.UserDetailContent
import com.edival.reciostore.presentation.screens.profile.info.components.DeleteAccount
import com.edival.reciostore.presentation.screens.profile.info.components.DownloadUserImg
import com.edival.reciostore.presentation.screens.profile.info.components.ProfileTopBar
import kotlinx.coroutines.CoroutineScope

@Composable
fun ProfileInfoScreen(
    navHostController: NavHostController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    vm: ProfileInfoViewModel = hiltViewModel()
) {
    Scaffold(scaffoldState = scaffoldState, topBar = {
        ProfileTopBar(navHostController, scope, scaffoldState)
    }) { padding ->
        vm.user?.let { user ->
            val ctx = LocalContext.current
            UserDetailContent(padding, vm.enabledBtn, user) {
                vm.downloadUserImg(user.img, ctx) {
                    Toast.makeText(ctx, R.string.theres_no_image_to_download, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
    DeleteAccount()
    DownloadUserImg()
}