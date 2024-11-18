package com.alexcao.aiary.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.time.LocalDate

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ExpenseInfo::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ExpenseInfo::class,
            parentColumns = ["id"],
            childColumns = ["expenseSourceId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("categoryId"),
        Index("expenseSourceId")
    ]
)
data class ExpenseInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val label: String,
    val amount: BigDecimal,
    val unit: String,
    val date: LocalDate,
    val categoryId: Int,
    val expenseSourceId: Int,
)