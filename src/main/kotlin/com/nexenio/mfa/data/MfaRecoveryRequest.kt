package com.nexenio.mfa.data

data class MfaRecoveryRequest(val authToken: String, val username: String, val recoveryPhrase: String)
