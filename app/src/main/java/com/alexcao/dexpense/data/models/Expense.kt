package com.alexcao.dexpense.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class Expense(
    @Embedded
    val info: ExpenseInfo,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: ExpenseCategory,

    @Relation(
        parentColumn = "expenseSourceId",
        entityColumn = "id"
    )
    val expenseSource: ExpenseSource
)