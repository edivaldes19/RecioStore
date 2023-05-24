package com.edival.reciostore.presentation.screens.admin.user.detail

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.components.UserDetailContent
import com.edival.reciostore.presentation.screens.admin.user.detail.components.DownloadUserImgByDetail

@Composable
fun AdminUserDetailScreen(
    navHostController: NavHostController, vm: AdminUserDetailViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        DefaultTopBar(titleRes = R.string.user_info, navHostController = navHostController)
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
    DownloadUserImgByDetail()
}