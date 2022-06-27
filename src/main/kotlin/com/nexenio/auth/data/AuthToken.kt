package com.nexenio.auth.data

import com.nexenio.util.Convert

data class AuthToken(val email: String, val secret: String) {
    fun base64(): String {
        val ascii = "$email;$secret"
        return Convert.asciiToBase64(ascii)
    }

    companion object {
        fun fromBase64(base64: String): AuthToken {
            val ascii = Convert.base64ToAscii(base64)
            val parts = ascii.split(";")
            require(parts.size == 2) { "bad input $ascii" }
            return AuthToken(parts[0], parts[1])
        }
    }
}
