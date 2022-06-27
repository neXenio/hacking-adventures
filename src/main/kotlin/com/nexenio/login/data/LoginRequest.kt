package com.nexenio.login.data

data class LoginRequest(val email: String, val password: String, val mfaCode: String)
