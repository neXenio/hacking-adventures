package com.nexenio.reset.api

import com.nexenio.reset.data.ResetRequest
import com.nexenio.reset.service.ResetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ResetController {
    @Autowired
    private lateinit var resetService: ResetService

    @PostMapping("/reset")
    fun reset(@RequestBody request: ResetRequest) {
        resetService.reset(request)
    }
}
