package com.nexenio.util

import de.mkammerer.argon2.Argon2Factory
import org.apache.commons.codec.digest.Md5Crypt
import java.nio.charset.Charset
import java.security.MessageDigest


object Hashing {
    // adapted from https://mkyong.com/java/java-password-hashing-with-argon2/
    private val argon2 = Argon2Factory.createAdvanced()

    fun argon2(pw: String, salt: ByteArray): String {
        return argon2.hash(1, 65536, 10, pw.toCharArray(), Charset.defaultCharset(), salt)
    }

    // adapted from https://gist.github.com/lovubuntu/164b6b9021f5ba54cefc67f60f7a1a25
    private val sha256 = MessageDigest.getInstance("SHA-256")

    fun sha256(pw: String, salt: String): String {
        val bytes = (pw + salt).toByteArray()
        val digest = sha256.digest(bytes)
        val result = Convert.bytesToBase64(digest)
        return "\$$salt\$$result"
    }

    fun md5Crypt(pw: String, salt: String): String {
        return Md5Crypt.md5Crypt(pw.toByteArray(), "\$1\$$salt")
    }
}
