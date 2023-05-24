package com.edival.reciostore.presentation.screens.loading.components

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.edival.reciostore.BuildConfig
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Info
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.screen.auth.AuthScreen

@Composable
fun LoadingAppContent(
    padding: PaddingValues, info: Info, user: User?, onNav: @Composable (String) -> Unit
) {
    val ctx = LocalContext.current
    val activity = ctx as? Activity
    if (!info.value.isNullOrBlank()) {
        if (BuildConfig.VERSION_NAME == info.value) {
            if (user != null) {
                user.roles?.let { roles ->
                    if (roles.size > 1) onNav(Graph.ROLES)
                    else {
                        val singleRole = roles.first()
                        if (!singleRole.route.isNullOrBlank()) onNav(singleRole.route)
                    }
                }
            } else onNav(AuthScreen.LogIn.route)
        } else DialogAppUpdate(activity, padding, info.value)
    } else {
        activity?.finishAndRemoveTask()
        Toast.makeText(ctx, R.string.error_connecting_server, Toast.LENGTH_SHORT).show()
    }
}