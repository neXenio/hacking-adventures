package com.nexenio.user.data

data class UserData(
    val username: String,
    val salt: String,
    val pwHash: String,
    val phone: String = "",
    val hasMfa: Boolean = true,
    val isAdmin: Boolean = false
)
