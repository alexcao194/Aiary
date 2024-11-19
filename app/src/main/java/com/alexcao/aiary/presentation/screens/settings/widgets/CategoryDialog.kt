package com.alexcao.aiary.presentation.screens.settings.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.alexcao.aiary.R
import com.alexcao.aiary.data.models.ExpenseCategory
import com.alexcao.aiary.presentation.commons.FilledTextField
import com.alexcao.aiary.ui.theme.badgeLights
import com.alexcao.aiary.ui.theme.badges
import com.alexcao.aiary.utils.requiredValidator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onSave: (ExpenseCategory) -> Unit,
    onUpdate: (ExpenseCategory) -> Unit,
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

    var error by remember { mutableStateOf(requiredValidator(category.name)) }

    BasicAlertDialog(
        modifier = modifier.fillMaxWidth(),
        onDismissRequest = { onDismissRequest() }
    ) {
        val labelFocusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current

        LaunchedEffect(Unit) {
            labelFocusRequester.requestFocus()
        }

        Column {
            Column(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = CardDefaults.shape
                    )
                    .padding(16.dp)
            ) {
                FilledTextField(
                    modifier = Modifier.focusRequester(labelFocusRequester),
                    value = category.name,
                    onValueChange = {
                        category = category.copy(name = it)
                        error = requiredValidator(it)
                    },
                    hint = stringResource(R.string.label_hint),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow {
                    items(badgeLights) { badgeColor ->
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .clip(CircleShape)
                                .background(color = badgeColor)
                                .size(32.dp)
                                .clickable {
                                    category = category.copy(tint = badgeColor)
                                },

                            contentAlignment = Alignment.Center
                        ) {
                            if (badgeColor.toArgb() == category.tint.toArgb()) {
                                Icon(
                                    imageVector = Icons.Rounded.Check,
                                    contentDescription = "Selected",
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                if (initialCategory != null) FilledTonalButton(
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onErrorContainer,
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ),
                    onClick = {
                        onDismissRequest()
                        onDelete(category)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.delete),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                FilledTonalButton(
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onErrorContainer,
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ),
                    onClick = { onDismissRequest() }
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                FilledTonalButton(
                    enabled = error == null,
                    colors = ButtonDefaults.textButtonColors(
                        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(
                            alpha = 0.5f
                        ),
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    onClick = {
                        onDismissRequest()
                        if (initialCategory != null)
                            onUpdate(category)
                        else
                            onSave(category)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.save),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}