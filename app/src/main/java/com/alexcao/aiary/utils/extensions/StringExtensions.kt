package com.alexcao.aiary.utils.extensions

fun String.toTitleCase(): String {
    return this.split(" ") // Split by space to get words
        .joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
}
