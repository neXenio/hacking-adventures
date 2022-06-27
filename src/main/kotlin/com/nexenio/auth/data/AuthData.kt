package com.nexenio.auth.data

data class AuthData(val token: AuthToken, val isAdmin: Boolean, val timestamp: Long)
