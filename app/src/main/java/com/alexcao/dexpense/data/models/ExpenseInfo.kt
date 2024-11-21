package com.alexcao.dexpense.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.time.LocalDate

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = SourceInfo::class,
            parentColumns = ["id"],
            childColumns = ["expenseSourceId"],
            onDelete = ForeignKey.RESTRICT
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