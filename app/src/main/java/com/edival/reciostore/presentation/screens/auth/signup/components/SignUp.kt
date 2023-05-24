package com.edival.reciostore.presentation.screens.auth.signup.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.screens.auth.signup.SignUpViewModel

@Composable
fun SignUp(navHostController: NavHostController, vm: SignUpViewModel = hiltViewModel()) {
    when (val logInResponse = vm.signUpResource) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            vm.createToken()
            when (val tokenResponse = vm.tokenResponse) {
                Resource.Loading -> DefaultProgressBar(R.string.getting_notifications_token)
                is Resource.Success -> {
                    vm.updateNotificationToken(logInResponse.data.user?.id, tokenResponse.data)
                    when (val updateNotTokResponse = vm.updateNotTokResponse) {
                        Resource.Loading -> DefaultProgressBar(R.string.saving_notification_token)
                        is Resource.Success -> {
                            LaunchedEffect(Unit) {
                                vm.clearForm()
                                logInResponse.data.user = updateNotTokResponse.data
                                vm.saveSession(logInResponse.data)
                                logInResponse.data.user?.roles?.let { roles ->
                                    if (roles.size > 1) {
                                        navHostController.navigate(Graph.ROLES) {
                                            popUpTo(Graph.AUTH) { inclusive = true }
                                        }
                                    } else {
                                        navHostController.navigate(Graph.CLIENT) {
                                            popUpTo(Graph.AUTH) { inclusive = true }
                                        }
                                    }
                                }
                            }
                        }

                        is Resource.Failure -> {
                            vm.clearForm()
                            Toast.makeText(
                                LocalContext.current,
                                updateNotTokResponse.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
                            updateNotTokResponse?.let {
                                vm.clearForm()
                                Toast.makeText(
                                    LocalContext.current, R.string.unknown_error, Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }

                is Resource.Failure -> {
                    vm.clearForm()
                    Toast.makeText(LocalContext.current, tokenResponse.message, Toast.LENGTH_SHORT)
                        .show()
                }

                else -> {
                    tokenResponse?.let {
                        vm.clearForm()
                        Toast.makeText(
                            LocalContext.current, R.string.unknown_error, Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        is Resource.Failure -> {
            vm.clearForm()
            Toast.makeText(LocalContext.current, logInResponse.message, Toast.LENGTH_SHORT).show()
        }

        else -> {
            logInResponse?.let {
                vm.clearForm()
                Toast.makeText(LocalContext.current, R.string.unknown_error, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}