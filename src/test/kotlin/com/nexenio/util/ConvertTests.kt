package com.nexenio.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ConvertTests {
    @Test
    fun `convert ASCII to HEX passes`() {
        assertThat(Convert.asciiToHex("Hello World!")).isEqualTo("48656c6c6f20576f726c6421")
    }

    @Test
    fun `convert ASCII to BASE64 passes`() {
        assertThat(Convert.asciiToBase64("Hello World!")).isEqualTo("SGVsbG8gV29ybGQh")
    }

    @Test
    fun `convert HEX to BASE64 passes`() {
        val input = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
        val output = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"
        assertThat(Convert.hexToBase64(input)).isEqualTo(output)
    }

    @Test
    fun `convert HEX to ASCII passes`() {
        assertThat(Convert.hexToAscii("48656c6c6f20576f726c6421")).isEqualTo("Hello World!")
    }

    @Test
    fun `convert BASE64 to HEX passes`() {
        val output = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
        val input = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"
        assertThat(Convert.base64ToHex(input)).isEqualTo(output)
    }

    @Test
    fun `convert BASE64 to ASCII passes`() {
        assertThat(Convert.base64ToAscii("SGVsbG8gV29ybGQh")).isEqualTo("Hello World!")
    }

    @ParameterizedTest(name = "convert ASCII back and forth should return true for {0}")
    @ValueSource(strings = ["foo", "bar", "fiZzBUzz", ""])
    fun `conversion works both ways for ASCII`(candidate: String) {
        assertThat(Convert.hexToAscii(Convert.asciiToHex(candidate))).isEqualTo(candidate)
        assertThat(Convert.base64ToAscii(Convert.asciiToBase64(candidate))).isEqualTo(candidate)
    }

    @ParameterizedTest(name = "convert HEX back and forth should return true for {0}")
    @ValueSource(strings = ["48656c6c6f20576f726c6421", "666f6f626172", ""])
    fun `conversion works both ways for HEX`(candidate: String) {
        assertThat(Convert.asciiToHex(Convert.hexToAscii(candidate))).isEqualTo(candidate)
        assertThat(Convert.base64ToHex(Convert.hexToBase64(candidate))).isEqualTo(candidate)
    }

    @ParameterizedTest(name = "convert BASE64 back and forth should return true for {0}")
    @ValueSource(strings = ["SGVsbG8gV29ybGQh", "Zml6emJ1eno=", ""])
    fun `conversion works both ways for BASE64`(candidate: String) {
        assertThat(Convert.asciiToBase64(Convert.base64ToAscii(candidate))).isEqualTo(candidate)
        assertThat(Convert.hexToBase64(Convert.base64ToHex(candidate))).isEqualTo(candidate)
    }
}
