package com.alexcao.dexpense.utils.extensions

import java.time.LocalDate

fun LocalDate.isSameDay(other: LocalDate): Boolean {
    return this.year == other.year && this.dayOfYear == other.dayOfYear
}

val LocalDate.daysInMonth: List<LocalDate>
    get() {
        val daysInMonth = mutableListOf<LocalDate>()
        val currentDay = LocalDate.of(this.year, this.month, 1)
        val lastDay = currentDay.plusMonths(1).minusDays(1)

        var current = currentDay
        while (current.isBefore(lastDay) || current.isEqual(lastDay)) {
            daysInMonth.add(current)
            current = current.plusDays(1)
        }

        return daysInMonth
    }
