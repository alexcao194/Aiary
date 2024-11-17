package com.alexcao.aiary.presentation.screens.settings.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.alexcao.aiary.data.models.ExpenseCategory
import com.alexcao.aiary.ui.theme.badgeLights

@Composable
fun CategoryDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onSave: (ExpenseCategory) -> Unit,
    onDelete: (ExpenseCategory) -> Unit,
    initialCategory: ExpenseCategory? = null
) {
    var category by remember {
        mutableStateOf(
            initialCategory ?: ExpenseCategory(
                name = "",
                tint = badgeLights.first()
            )
        )
    }
    BadgeDialog(
        modifier = modifier,
        onDismissRequest = { onDismissRequest() },
        onChange = { name, color -> category = category.copy(name = name, tint = color) },
        onSave = { onSave(category) },
        onDelete = if (initialCategory != null) { { onDelete(category) } } else { null },
        colors = badgeLights,
        label = category.name,
        color = category.tint
    )
}