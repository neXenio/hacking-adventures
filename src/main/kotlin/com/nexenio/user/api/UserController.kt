package com.nexenio.user.api

import com.nexenio.auth.service.AuthService
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

    @GetMapping("/users")
    fun getUser(@RequestBody request: UserRequest): UserResponse {
        authService.validate(request.email, request.authToken)
        val user = userService.get(request.email)
        return UserResponse(user.email, user.phone, user.hasMfa)
    }
}
