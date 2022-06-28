package com.nexenio.login.data

data class LoginRequest(val username: String, val password: String, val mfaCode: String)
