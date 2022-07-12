package com.nexenio.reset.service

import com.nexenio.auth.service.AuthService
import com.nexenio.reset.data.ResetRequest
import com.nexenio.user.service.UserService
import com.nexenio.util.Hashing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ResetService {
    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var userService: UserService

    fun reset(request: ResetRequest) {
        require(authService.authenticate(request.authToken)) { "invalid auth" }

        val user = userService.get(request.username)

        userService.save(user.copy(pwHash = Hashing.sha(user.salt + request.newPassword)))
    }
}
