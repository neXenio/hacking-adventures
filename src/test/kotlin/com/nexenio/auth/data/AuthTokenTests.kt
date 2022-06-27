package com.nexenio.auth.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AuthTokenTests {
    @Test
    fun `convert token to base64 passes`() {
        val token = AuthToken("test@test.de", "foobar")
        val base64 = token.base64()

        assertThat(base64).isEqualTo("dGVzdEB0ZXN0LmRlO2Zvb2Jhcg==")
        assertThat(AuthToken.fromBase64(base64)).isEqualTo(token)
    }
}
