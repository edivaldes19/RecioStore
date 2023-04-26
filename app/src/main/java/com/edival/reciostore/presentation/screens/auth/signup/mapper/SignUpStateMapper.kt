package com.edival.reciostore.presentation.screens.auth.signup.mapper

import com.edival.reciostore.domain.model.User
import com.edival.reciostore.presentation.screens.auth.signup.SignUpState

fun SignUpState.toUser(): User {
    return User(
        name = name, surname = surname, phone = phone, email = email, password = password
    )
}