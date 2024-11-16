package com.alexcao.aiary.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ExpenseInfo::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = ExpenseInfo::class,
            parentColumns = ["id"],
            childColumns = ["expenseSourceId"],
            onDelete = CASCADE
        )
    ]
)
data class ExpenseInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val label: String,
    val amount: Double,
    val unit: String,
    val date: LocalDate,
    val categoryId: Int,
    val expenseSourceId: Int,
)