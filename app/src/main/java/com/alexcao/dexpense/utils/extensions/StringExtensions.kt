package com.alexcao.dexpense.utils.extensions

import java.math.BigDecimal
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

fun String.toCurrency(currencySymbol: String? = null, isNegative : Boolean? = null): String {
    val formatedText = try {
        val formatter = DecimalFormat("#,###")
        when {
            isNegative == null -> formatter.format(this.toBigDecimal())
            isNegative -> "-${formatter.format(this.toBigDecimal().abs())}"
            else -> formatter.format(this.toBigDecimal().abs())
        }
    } catch (e: NumberFormatException) {
        "0"
    }
    return if (currencySymbol != null) "$formatedText $currencySymbol" else formatedText
}

fun String.toAmount(): BigDecimal {
    return this.replace(",", "").toBigDecimal()
}