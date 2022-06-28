package com.nexenio.login.service

import com.nexenio.auth.service.AuthService
import com.nexenio.login.data.LoginRequest
import com.nexenio.login.data.LoginResponse
import com.nexenio.mfa.service.MfaVerifyService
import com.nexenio.user.service.UserService
import com.nexenio.util.Hashing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class LoginService {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var mfaVerifyService: MfaVerifyService

    @Autowired
    private lateinit var authService: AuthService

    fun login(@RequestBody request: LoginRequest): LoginResponse {
        val user = userService.get(request.username)

        require(Hashing.sha(request.password) == user.pwHash) { "invalid login credentials" }

        if (user.hasMfa) {
            require(mfaVerifyService.verify(user.username, request.mfaCode)) { "invalid MFA" }
        }

        println("yes")
        return LoginResponse(authService.issueToken(user.username, user.isAdmin))
    }
}
