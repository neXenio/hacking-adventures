package com.nexenio.mfa.data

data class MfaSetupResult(val secret: String, val imageData: String, val recoveryPhrase: String)
