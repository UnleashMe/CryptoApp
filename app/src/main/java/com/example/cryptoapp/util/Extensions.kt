package com.example.cryptoapp.util

fun String?.safelyToDouble(): Double? {
    this?.let { s ->
        if (s.all { it.isDigit() || it == '.' || it == '-' || it == '+' } && s.any { it.isDigit() }) {
            return s.toDouble()
        }
    }
    return null
}