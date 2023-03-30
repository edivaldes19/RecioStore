package com.edival.reciostore.presentation.screens.profile.update.mapper

import com.edival.reciostore.domain.model.User
import com.edival.reciostore.presentation.screens.profile.update.ProfileUpdateState

fun ProfileUpdateState.toUser(): User {
    return User(name = name, surname = surname, phone = phone, img = img)
}