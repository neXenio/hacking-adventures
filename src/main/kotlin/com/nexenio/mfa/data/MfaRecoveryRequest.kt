package com.nexenio.mfa.data

data class MfaRecoveryRequest(val authToken: String, val email: String, val recoveryPhrase: String)
