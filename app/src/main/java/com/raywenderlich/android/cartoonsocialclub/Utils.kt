package com.raywenderlich.android.cartoonsocialclub

fun String.uppercasedInitial(): String {
    if (isNotEmpty()) {
        return substring(0, 1).toUpperCase()
    }
    return ""
}