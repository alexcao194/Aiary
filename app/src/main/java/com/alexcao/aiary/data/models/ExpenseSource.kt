package com.alexcao.aiary.data.models

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseSource(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val expenseId: Int,
    @DrawableRes
    val drawable: Int
)
