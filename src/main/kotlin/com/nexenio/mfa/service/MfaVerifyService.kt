package com.nexenio.mfa.service

import com.nexenio.mfa.data.MfaDb
import dev.samstevens.totp.code.CodeVerifier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MfaVerifyService {
    @Autowired
    private lateinit var verifier: CodeVerifier

    @Autowired
    private lateinit var mfaDb: MfaDb

    fun verify(email: String, code: String): Boolean {
        val secret = mfaDb.get(email).secret

        return verifier.isValidCode(secret, code)
    }
}
