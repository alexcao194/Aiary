package com.alexcao.dexpense.core.database.converters

import androidx.room.TypeConverter
import java.math.BigInteger

class BigIntegerConverter {
    @TypeConverter
    fun fromBigDecimal(value: BigInteger): String {
        return value.toString()
    }

    @TypeConverter
    fun toBigDecimal(value: String): BigInteger {
        return BigInteger(value)
    }
}