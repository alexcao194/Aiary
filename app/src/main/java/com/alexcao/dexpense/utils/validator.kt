package com.alexcao.dexpense.utils

fun requiredValidator(value: String = ""): String? {
    return if (value.isEmpty()) "This field is required" else null
}