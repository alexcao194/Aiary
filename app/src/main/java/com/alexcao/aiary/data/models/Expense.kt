package com.alexcao.aiary.data.models

import java.time.LocalDate

data class Expense(
    val id: Int,
    val label: String,
    val amount: Double,
    val unit: String,
    val date: LocalDate,
    val category: Category,
    val source: ExpanseSource
)
