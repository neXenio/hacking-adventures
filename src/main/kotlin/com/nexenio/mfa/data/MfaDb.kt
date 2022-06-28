package com.nexenio.mfa.data

import org.springframework.stereotype.Component

@Component
class MfaDb {
    private val db = mutableMapOf<String, MfaData>()

    fun save(data: MfaData) {
        db[data.username] = data
    }

    fun get(username: String): MfaData {
        return db[username]!!
    }
}
