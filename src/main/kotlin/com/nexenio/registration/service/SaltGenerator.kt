package com.nexenio.registration.service

import com.nexenio.util.Hashing
import java.util.UUID

object SaltGenerator {
    fun getNextSalt(): String {
        return Hashing.sha(UUID.randomUUID().toString()).substring(0, 12)
    }
}
