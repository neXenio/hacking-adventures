package com.nexenio.integration

import com.nexenio.login.data.LoginRequest
import com.nexenio.login.data.LoginResponse
import com.nexenio.mfa.data.MfaRecoveryRequest
import com.nexenio.mfa.data.MfaRecoveryResponse
import com.nexenio.registration.data.RegistrationRequest
import com.nexenio.registration.data.RegistrationResponse
import com.nexenio.reset.data.ResetRequest
import dev.samstevens.totp.code.CodeGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.UUID

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {
    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var codeGenerator: CodeGenerator

    private fun register(username: String, password: String): ResponseEntity<RegistrationResponse> {
        val request = RegistrationRequest(username, password)
        return restTemplate.postForEntity("/register", request, RegistrationResponse::class.java)
    }

    private fun login(username: String, password: String, mfaSecret: String): ResponseEntity<LoginResponse> {
        val mfaCounter = Math.floorDiv(System.currentTimeMillis() / 1000, 30)
        val mfaCode = if (mfaSecret.isBlank()) "" else codeGenerator.generate(mfaSecret, mfaCounter)
        val request = LoginRequest(username, password, mfaCode)
        return restTemplate.postForEntity("/login", request, LoginResponse::class.java)
    }

    private fun reset(authToken: String, username: String, newPassword: String): ResponseEntity<Void> {
        val request = ResetRequest(authToken, username, newPassword)
        return restTemplate.postForEntity("/reset", request, Void::class.java)
    }

    private fun <T> recover(authToken: String, username: String, recoveryPhrase: String, responseType: Class<T>): ResponseEntity<T> {
        val request = MfaRecoveryRequest(authToken, username, recoveryPhrase)
        return restTemplate.postForEntity("/recover", request, responseType)
    }

    private fun makeUserName(): String {
        val result = "test${UUID.randomUUID().hashCode()}"
        println(result)
        return result
    }

    @Test
    fun registerNewUser_passes() {
        val username = makeUserName()
        val password = "password1"
        val response = register(username, password)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isNotNull
        assertThat(response.body!!.authToken).isNotBlank
        assertThat(response.body!!.mfaSecret).isNotBlank
        assertThat(response.body!!.mfaQr).isNotBlank
        assertThat(response.body!!.recoveryPhrase).isNotBlank

        println(response.body!!.recoveryPhrase)
    }

    @Test
    fun login_passes() {
        val username = makeUserName()
        val password = "password2"

        val registerResponse = register(username, password).body!!
        val loginResponse = login(username, password, registerResponse.mfaSecret)

        assertThat(loginResponse.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(loginResponse.body).isNotNull
        assertThat(loginResponse.body!!.authToken).isNotBlank
    }

    @Test
    fun resetPassword_passes() {
        val username = makeUserName()
        val oldPassword = "password3"
        val newPassword = "password4"
        val registerResponse = register(username, oldPassword).body!!
        val authToken = registerResponse.authToken

        val resetResponse = reset(authToken, username, newPassword)
        assertThat(resetResponse.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun recoverMfa_passes() {
        val username = makeUserName()
        val password = "password5"
        val registerResponse = register(username, password).body!!
        val authToken = registerResponse.authToken
        val recoveryPhrase = registerResponse.recoveryPhrase

        val recoverResponse = recover(authToken, username, recoveryPhrase, MfaRecoveryResponse::class.java)
        assertThat(recoverResponse.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(recoverResponse.body).isNotNull
        assertThat(recoverResponse.body!!.secret).isEqualTo(registerResponse.mfaSecret)
        assertThat(recoverResponse.body!!.imageData).isEqualTo(registerResponse.mfaQr)
    }

    @Test
    fun recoverMfa_forOtherUser_fails() {
        val username = makeUserName()
        val password = "password6"
        val registerResponse = register(username, password).body!!
        val authToken = registerResponse.authToken
        val recoveryPhrase = registerResponse.recoveryPhrase

        val recoverResponse = recover(authToken, "bob", recoveryPhrase, String::class.java)
        assertThat(recoverResponse.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }
}
