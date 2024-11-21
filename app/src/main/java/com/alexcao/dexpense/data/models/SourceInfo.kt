package com.alexcao.dexpense.data.models

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
data class SourceInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val tint: Color,
)
