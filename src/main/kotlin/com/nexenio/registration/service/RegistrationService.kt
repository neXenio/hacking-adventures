package com.nexenio.registration.service

import com.nexenio.auth.service.AuthService
import com.nexenio.mfa.data.MfaSetupResult
import com.nexenio.mfa.service.MfaSetupService
import com.nexenio.registration.data.RegistrationRequest
import com.nexenio.registration.data.RegistrationResponse
import com.nexenio.user.data.UserData
import com.nexenio.user.service.UserService
import com.nexenio.util.Hashing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class RegistrationService {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var mfaSetupService: MfaSetupService

    @Autowired
    private lateinit var authService: AuthService

    fun register(@RequestBody request: RegistrationRequest): RegistrationResponse {
        require(!userService.exists(request.username)) { "${request.username} already taken" }

        val username = request.username
        val salt = SaltGenerator.getNextSalt()
        val pwHash = Hashing.sha(salt + request.password)
        val phone = request.phone
        val hasMfa = request.hasMfa
        val user = UserData(username, salt, pwHash, phone, request.hasMfa)
        userService.save(user)

        val authToken = authService.issueToken(username)
        val mfaSetup = if (hasMfa) mfaSetupService.setupDevice(username) else MfaSetupResult("", "", "")

        return RegistrationResponse(authToken, mfaSetup.secret, mfaSetup.imageData, mfaSetup.recoveryPhrase)
    }
}
