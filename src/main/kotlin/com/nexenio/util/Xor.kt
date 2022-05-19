package com.nexenio.util

import kotlin.experimental.xor

object Xor {
    fun hex(a: String, b: String): String {
        val bytesA = Convert.hexToBytes(a)
        val bytesB = Convert.hexToBytes(b)

        val xoredBytes = bytesA.indices.map { bytesA[it].xor(bytesB[it]) }.toByteArray()

        return Convert.bytesToHex(xoredBytes)
    }
}
