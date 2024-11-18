package com.alexcao.aiary.presentation.screens.settings.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.alexcao.aiary.data.models.ExpenseSource
import com.alexcao.aiary.ui.theme.badges

@Composable
fun SourceDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onSave: (ExpenseSource) -> Unit,
    onUpdate: (ExpenseSource) -> Unit,
    onDelete: (ExpenseSource) -> Unit,
    initialSource: ExpenseSource? = null
) {
    var source by remember {
        mutableStateOf(
            initialSource ?: ExpenseSource(
                name = "",
                tint = badges.first()
            )
        )
    }
    BadgeDialog(
        modifier = modifier,
        onDismissRequest = { onDismissRequest() },
        onChange = { name, color -> source = source.copy(name = name, tint = color) },
        onSave = {
            if (initialSource != null) {
                onUpdate(source)
            } else {
                onSave(source)
            }
        },
        onDelete = if (initialSource != null) {
            { onDelete(source) }
        } else {
            null
        },
        colors = badges,
        label = source.name,
        color = source.tint
    )
}