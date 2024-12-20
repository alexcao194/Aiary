package com.alexcao.dexpense.core.database.converters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateConverter {
    // Convert LocalDate to String (ISO format)
    @TypeConverter
    fun fromLocalDate(localDate: LocalDate?): String? {
        return localDate?.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    // Convert String to LocalDate
    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE) }
    }
}
