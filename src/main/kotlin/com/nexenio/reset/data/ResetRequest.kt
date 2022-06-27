package com.nexenio.reset.data

data class ResetRequest(
    val authToken: String,
    val email: String,
    val newPassword: String
)
