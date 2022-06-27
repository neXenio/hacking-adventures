package com.nexenio.registration.data

data class RegistrationResponse(
    val authToken: String,
    val mfaSecret: String,
    val mfaQr: String,
    val recoveryPhrase: String
)
