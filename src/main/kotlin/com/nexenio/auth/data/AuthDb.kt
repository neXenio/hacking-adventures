package com.nexenio.auth.data

import org.springframework.stereotype.Component

@Component
class AuthDb {
    private val db = mutableMapOf<String, AuthData>()

    fun save(data: AuthData) {
        db[data.token.username] = data
    }

    fun get(username: String): AuthData {
        return db[username]!!
    }
}
