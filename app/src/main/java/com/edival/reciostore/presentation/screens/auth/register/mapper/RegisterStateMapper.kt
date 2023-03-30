package com.edival.reciostore.presentation.screens.auth.register.mapper

import com.edival.reciostore.domain.model.User
import com.edival.reciostore.presentation.screens.auth.register.RegisterState

fun RegisterState.toUser(): User {
    return User(
        name = name, surname = surname, phone = phone, email = email, password = password
    )
}