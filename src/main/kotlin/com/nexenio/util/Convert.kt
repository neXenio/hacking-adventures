package com.nexenio.util

import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex
import java.nio.charset.Charset


object Convert {
    private val DEFAULT_CHARSET = Charset.defaultCharset()

    // ASCII to X
    fun asciiToBytes(ascii: String): ByteArray = ascii.toByteArray(DEFAULT_CHARSET)

    fun asciiToHex(ascii: String): String = ascii.let(this::asciiToBytes).let(this::bytesToHex)

    fun asciiToBase64(ascii: String): String = ascii.let(this::asciiToBytes).let(this::bytesToBase64)

    // HEX to X
    fun hexToBytes(hex: String): ByteArray = hex.let(Hex::decodeHex)

    fun hexToBase64(hex: String): String = hex.let(this::hexToBytes).let(this::bytesToBase64)

    fun hexToAscii(hex: String): String = hex.let(this::hexToBytes).let(this::bytesToAscii)

    // BASE64 to X
    fun base64ToBytes(base64: String): ByteArray = base64.let(Base64::decodeBase64)

    fun base64ToHex(base64: String): String = base64.let(this::base64ToBytes).let(this::bytesToHex)

    fun base64ToAscii(base64: String): String = base64.let(this::base64ToBytes).let(this::bytesToAscii)

    // BYTES to X
    fun bytesToAscii(bytes: ByteArray): String = bytes.toString(DEFAULT_CHARSET)

    fun bytesToHex(bytes: ByteArray): String = bytes.let(Hex::encodeHexString)

    fun bytesToBase64(bytes: ByteArray): String = bytes.let(Base64::encodeBase64String)
}
