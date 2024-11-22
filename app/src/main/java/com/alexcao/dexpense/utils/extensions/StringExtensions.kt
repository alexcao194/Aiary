package com.alexcao.dexpense.utils.extensions

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.DecimalFormat

fun String.toTitleCase(): String {
    return this.split(" ") // Split by space to get words
        .joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
}

fun String.encode(): String {
    return URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
}

fun String.decode(): String {
    return URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
}

fun String.toCurrency(currencySymbol: String): String {
    return try {
        val formatter = DecimalFormat("#,###")
        val formattedAmount = formatter.format(this.toDouble())
        "$formattedAmount $currencySymbol"
    } catch (e: NumberFormatException) {
        "0 $currencySymbol"
    }
}

fun String.toAmount(): Double {
    return this.replace(",", "").toDouble()
}