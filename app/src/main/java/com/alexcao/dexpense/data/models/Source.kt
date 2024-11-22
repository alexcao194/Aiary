package com.alexcao.dexpense.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class Source(
    @Embedded
    val info: SourceInfo = SourceInfo(),

    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val amount: SourceAmount = SourceAmount()
)
