package com.nexenio.util

import java.io.File

object Files {
    /**
     * Returns an iterator over the lines in the file.
     * Loading an entire file into memory would blow up the heap.
     */
    fun readLines(fileName: String): Sequence<String> = File(fileName).inputStream().bufferedReader().lineSequence()
}
