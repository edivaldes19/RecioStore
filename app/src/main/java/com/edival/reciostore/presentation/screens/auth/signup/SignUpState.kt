package com.edival.reciostore.presentation.screens.auth.signup

data class SignUpState(
    val name: String = "",
    val surname: String = "",
    val phone: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)