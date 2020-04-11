package com.jpaya.core.extensions

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Unit test for ByteArrayExtensions functions
 */
class ByteArrayExtensionsTest {

    @Test
    fun encodeToHexText() {
        val originalText = "Expected string"
        val expectedHexadecimal = "457870656374656420737472696e67"
        assertEquals(expectedHexadecimal, originalText.toByteArray().toHex())
    }

    @Test
    fun encodeToHexSpecialCharactersText() {
        val originalText = "^/4(0!§±§.,=+-'~`"
        val expectedHexadecimal = "5e2f34283021c2a7c2b1c2a72e2c3d2b2d277e60"
        assertEquals(expectedHexadecimal, originalText.toByteArray().toHex())
    }

    @Test
    fun encodeToHexEmptyText() {
        val originalText = ""
        val expectedHexadecimal = ""
        assertEquals(expectedHexadecimal, originalText.toByteArray().toHex())
    }

    @Test
    fun encodeToHexUnexpectedText() {
        val originalText = "Not expected string"
        val expectedHexadecimal = "457870656374656420737472696e67"
        assertNotEquals(expectedHexadecimal, originalText.toByteArray().toHex())
    }
}
