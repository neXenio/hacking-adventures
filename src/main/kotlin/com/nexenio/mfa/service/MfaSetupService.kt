package com.nexenio.mfa.service

import com.nexenio.mfa.data.MfaData
import com.nexenio.mfa.data.MfaDb
import com.nexenio.mfa.data.MfaSetupResult
import dev.samstevens.totp.qr.QrDataFactory
import dev.samstevens.totp.qr.QrGenerator
import dev.samstevens.totp.secret.SecretGenerator
import dev.samstevens.totp.util.Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class MfaSetupService {

    @Autowired
    private lateinit var secretGenerator: SecretGenerator

    @Autowired
    private lateinit var qrDataFactory: QrDataFactory

    @Autowired
    private lateinit var qrGenerator: QrGenerator

    @Autowired
    private lateinit var mfaDb: MfaDb

    @PostConstruct
    fun insertAdmin() {
        setupDevice("bob")
    }

    fun setupDevice(email: String): MfaSetupResult {
        val secret = secretGenerator.generate()
        val recoveryPhrase = RecoveryPhrases.generate()
        mfaDb.save(MfaData(email, secret, recoveryPhrase))

        val imageData = makeImageData(email, secret)

        return MfaSetupResult(secret, imageData, recoveryPhrase)
    }

    fun makeImageData(email: String, secret: String): String {
        val data = qrDataFactory.newBuilder()
            .label(email)
            .secret(secret)
            .issuer("Super Secure Server")
            .build()

        // Generate the QR code image data as a base64 string which can be used in an <img> tag:
        return Utils.getDataUriForImage(
            qrGenerator.generate(data),
            qrGenerator.imageMimeType
        )
    }
}
