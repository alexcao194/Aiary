package com.alexcao.aiary.core.database.converters

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.TypeConverter

class ColorConverter {
    // Convert Color to ULong
    @TypeConverter
    fun fromColor(color: Color?): Int? {
        return color?.toArgb()
    }

    // Convert ULong to Color
    @TypeConverter
    fun toColor(argb: Int?): Color? {
        return argb?.let { Color(it) }
    }
}