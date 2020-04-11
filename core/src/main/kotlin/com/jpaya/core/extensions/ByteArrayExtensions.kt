package com.jpaya.core.extensions

/**
 * Convert any byte array to hexadecimal string.
 *
 * @return Hexadecimal string.
 */
fun ByteArray.toHex() = joinToString("") {
    "%02x".format(it)
}
