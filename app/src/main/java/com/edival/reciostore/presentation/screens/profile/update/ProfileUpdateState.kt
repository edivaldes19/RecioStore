package com.edival.reciostore.presentation.screens.profile.update

import android.net.Uri

data class ProfileUpdateState(
    val name: String = "",
    val surname: String = "",
    val phone: String = "",
    val img: String? = null,
    val imgSelected: Uri? = null
)