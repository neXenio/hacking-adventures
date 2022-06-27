package com.nexenio.login.api

import com.nexenio.login.data.LoginRequest
import com.nexenio.login.data.LoginResponse
import com.nexenio.login.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @Autowired
    private lateinit var loginService: LoginService

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): LoginResponse {
        return loginService.login(request)
    }
}
