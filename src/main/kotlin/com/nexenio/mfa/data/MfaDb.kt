package com.nexenio.mfa.data

import org.springframework.stereotype.Component

@Component
class MfaDb {
    private val db = mutableMapOf<String, MfaData>()

    fun save(data: MfaData) {
        db[data.email] = data
    }

    fun get(email: String): MfaData {
        return db[email]!!
    }
}
