package com.alexcao.aiary.data.models

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(indices = [Index(value = ["name"], unique = true)])
data class ExpenseSource(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val tint: Color,
    val amount: BigDecimal,
)
