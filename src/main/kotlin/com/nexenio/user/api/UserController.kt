package com.nexenio.user.api

import com.nexenio.auth.service.AuthService
import com.nexenio.mfa.service.MfaRecoveryService
import com.nexenio.user.data.UserRequest
import com.nexenio.user.data.UserResponse
import com.nexenio.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var mfaRecoveryService: MfaRecoveryService

    @GetMapping("/users")
    fun getUser(@RequestBody request: UserRequest): UserResponse {
        authService.authenticate(request.authToken)
        val user = userService.get(request.username)

        return if (authService.isAdmin(request.authToken)) {
            val recoveryPhrase = mfaRecoveryService.recoveryPhrase(request.username, request.authToken)
            UserResponse(user.username, user.salt, user.pwHash, user.phone, recoveryPhrase)
        } else {
            UserResponse(user.username, user.phone)
        }
    }
}
