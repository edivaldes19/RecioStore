package com.edival.reciostore.presentation.screens.auth.signup.mapper

import com.edival.reciostore.domain.model.User
import com.edival.reciostore.presentation.screens.auth.signup.SignUpState

fun SignUpState.toUser(): User {
    return User(
        name = name.trim(),
        surname = surname.trim(),
        phone = phone.trim(),
        email = email.trim(),
        password = password.trim()
    )
}