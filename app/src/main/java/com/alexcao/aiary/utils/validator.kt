package com.alexcao.aiary.utils

fun requiredValidator(value: String = ""): String? {
    return if (value.isEmpty()) "This field is required" else null
}