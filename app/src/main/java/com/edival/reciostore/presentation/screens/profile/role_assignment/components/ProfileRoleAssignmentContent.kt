package com.edival.reciostore.presentation.screens.profile.role_assignment.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Info
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.components.PasswordTextField
import com.edival.reciostore.presentation.screens.profile.role_assignment.ProfileRoleAssignmentViewModel

@Composable
fun ProfileRoleAssignmentContent(
    padding: PaddingValues,
    infoList: List<Info>,
    vm: ProfileRoleAssignmentViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    LaunchedEffect(key1 = vm.errorMessage) {
        if (vm.errorMessage.isNotBlank()) {
            Toast.makeText(ctx, vm.errorMessage, Toast.LENGTH_SHORT).show()
            vm.errorMessage = ""
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(all = dimensionResource(R.dimen.padding_default))
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        vm.getDataToShow(infoList).also { texts ->
            if (texts.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_min)),
                    text = texts.component1() ?: stringResource(R.string.unknown),
                    style = MaterialTheme.typography.h5
                )
                Text(
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_min)),
                    text = texts.component2() ?: stringResource(R.string.unknown)
                )
                PasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    value = vm.state.masterPassword,
                    onValueChange = { vm.onMasterPasswordInput(it) },
                    label = R.string.master_password
                )
                DefaultButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.padding_min)),
                    text = R.string.ok_msg,
                    enabled = vm.enabledBtn,
                    onClick = {
                        texts.last()?.let { masterPassword ->
                            vm.validateField(ctx, masterPassword) { isValid ->
                                if (isValid) vm.onUserRoleUpdate()
                            }
                        }
                    })
            }
        }
    }
}