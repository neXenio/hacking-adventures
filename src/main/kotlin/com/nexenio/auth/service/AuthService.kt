package com.nexenio.auth.service

import com.nexenio.auth.data.AuthData
import com.nexenio.auth.data.AuthDb
import com.nexenio.auth.data.AuthToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.UUID

@Service
class AuthService {
    private val expiration = Duration.ofMinutes(10).toMillis()

    @Autowired
    private lateinit var authDb: AuthDb

    fun issueToken(email: String, isAdmin: Boolean = false): String {
        val token = AuthToken(email, UUID.randomUUID().toString())
        val timestamp = System.currentTimeMillis()
        val auth = AuthData(token, isAdmin, timestamp)
        authDb.save(auth)

        return token.base64()
    }

    fun validate(email: String, token: String): Boolean {
        val authToken = AuthToken.fromBase64(token)
        val authData = authDb.get(authToken.email)

        // check secret
        if (authData.token.secret != authToken.secret) {
            return false
        }

        // check expiration
        val now = System.currentTimeMillis()
        if (now - authData.timestamp > expiration) {
            return false
        }

        // check authorization
        return authData.isAdmin || authToken.email == email
    }
}
