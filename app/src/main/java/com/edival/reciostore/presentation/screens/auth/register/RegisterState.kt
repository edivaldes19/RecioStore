package com.edival.reciostore.presentation.screens.auth.register

data class RegisterState(
    val name: String = "",
    val surname: String = "",
    val phone: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)