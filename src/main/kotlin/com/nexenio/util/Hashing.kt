package com.nexenio.util

import java.security.MessageDigest

object Hashing {
    fun sha(password: String): String {
        val sha256 = MessageDigest.getInstance("SHA-256")
        val encodedHash = sha256.digest(Convert.asciiToBytes(password))
        return Convert.bytesToHex(encodedHash)
    }
}
