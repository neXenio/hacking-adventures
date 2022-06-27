package com.nexenio.registration.data

data class RegistrationRequest(
    val email: String,
    val password: String,
    val phone: String = "",
    val hasMfa: Boolean = true
)
