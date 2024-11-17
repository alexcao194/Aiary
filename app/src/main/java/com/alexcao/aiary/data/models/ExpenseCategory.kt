package com.alexcao.aiary.data.models

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val tint: Color,
)
