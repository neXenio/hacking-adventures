package com.nexenio.mfa.api

import com.nexenio.mfa.data.MfaRecoveryRequest
import com.nexenio.mfa.data.MfaRecoveryResponse
import com.nexenio.mfa.service.MfaRecoveryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MfaRecoveryController {
    @Autowired
    private lateinit var mfaRecoveryService: MfaRecoveryService

    @PostMapping("/recover")
    fun recover(@RequestBody request: MfaRecoveryRequest): MfaRecoveryResponse {
        return mfaRecoveryService.recover(request)
    }
}
