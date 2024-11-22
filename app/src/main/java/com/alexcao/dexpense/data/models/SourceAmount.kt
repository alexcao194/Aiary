package com.alexcao.dexpense.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = SourceInfo::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class SourceAmount(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: BigDecimal = BigDecimal.ZERO
)
