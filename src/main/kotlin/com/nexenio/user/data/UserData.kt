package com.nexenio.user.data

data class UserData(
    val email: String,
    val pwHash: String,
    val phone: String = "",
    val hasMfa: Boolean = true,
    val isAdmin: Boolean = false
)
