package com.nexenio.registration.api

import com.nexenio.registration.data.RegistrationRequest
import com.nexenio.registration.data.RegistrationResponse
import com.nexenio.registration.service.RegistrationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegistrationController {

    @Autowired
    private lateinit var registrationService: RegistrationService

    @PostMapping("/register")
    fun register(@RequestBody request: RegistrationRequest): RegistrationResponse {
        return registrationService.register(request)
    }
}
