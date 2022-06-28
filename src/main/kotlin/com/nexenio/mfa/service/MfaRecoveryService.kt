package com.nexenio.mfa.service

import com.nexenio.auth.service.AuthService
import com.nexenio.mfa.data.MfaDb
import com.nexenio.mfa.data.MfaRecoveryRequest
import com.nexenio.mfa.data.MfaRecoveryResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MfaRecoveryService {
    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var mfaDb: MfaDb

    @Autowired
    private lateinit var mfaSetupService: MfaSetupService

    fun recover(request: MfaRecoveryRequest): MfaRecoveryResponse {
        require(authService.authenticate(request.authToken)) { "invalid auth" }

        val mfaData = mfaDb.get(request.username)

        if (!authService.isAdmin(request.authToken)) {
            require(mfaData.recoveryPhrase == request.recoveryPhrase) { "invalid recovery phrase" }
        }

        val imageData = mfaSetupService.makeImageData(mfaData.username, mfaData.secret)
        return MfaRecoveryResponse(mfaData.secret, imageData)
    }
}
