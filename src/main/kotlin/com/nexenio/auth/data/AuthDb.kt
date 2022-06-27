package com.nexenio.auth.data

import org.springframework.stereotype.Component

@Component
class AuthDb {
    private val db = mutableMapOf<String, AuthData>()

    fun save(data: AuthData) {
        db[data.token.email] = data
    }

    fun get(email: String): AuthData {
        return db[email]!!
    }
}
