package com.nexenio.user.data

import org.springframework.stereotype.Component

@Component
class UserDb {
    private val db = mutableMapOf<String, UserData>()

    fun save(user: UserData) {
        db[user.email] = user
    }

    fun get(email: String): UserData {
        return db[email]!!
    }

    fun exists(email: String): Boolean {
        return db.containsKey(email)
    }
}
