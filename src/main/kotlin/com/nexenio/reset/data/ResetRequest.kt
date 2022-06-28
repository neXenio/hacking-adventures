package com.nexenio.reset.data

data class ResetRequest(
    val authToken: String,
    val username: String,
    val newPassword: String
)
