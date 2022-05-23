package com.nexenio.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class XorTests {
    @Test
    fun `XORing passes`() {
        val output = Xor.hex("1c0111001f010100061a024b53535009181c", "686974207468652062756c6c277320657965")
        assertThat(output).isEqualTo("746865206b696420646f6e277420706c6179")
    }
}
