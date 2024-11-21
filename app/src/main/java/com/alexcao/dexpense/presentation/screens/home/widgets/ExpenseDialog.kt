package com.alexcao.dexpense.presentation.screens.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.alexcao.dexpense.R
import com.alexcao.dexpense.data.models.Expense
import com.alexcao.dexpense.data.models.Category
import com.alexcao.dexpense.data.models.ExpenseInfo
import com.alexcao.dexpense.data.models.SourceInfo
import com.alexcao.dexpense.presentation.commons.FilledTextField
import com.alexcao.dexpense.utils.requiredValidator
import java.math.BigDecimal
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDialog(
    modifier: Modifier = Modifier,
    localDate: LocalDate,
    initialExpense: Expense? = null,
    sourceInfos: List<SourceInfo>,
    categories: List<Category>,
    onDismissRequest: () -> Unit,
    onSave: (Expense) -> Unit,
    onUpdate: (Expense) -> Unit,
    onDelete: (Expense) -> Unit
) {
    BasicAlertDialog(
        modifier = modifier.fillMaxWidth(),
        onDismissRequest = { onDismissRequest() }
    ) {
        var expense by remember {
            mutableStateOf(
                initialExpense ?: Expense(
                    sourceInfo = sourceInfos.first(),
                    category = categories.first(),
                    info = ExpenseInfo(
                        expenseSourceId = sourceInfos.first().id,
                        categoryId = categories.first().id,
                        amount = BigDecimal(0.0),
                        unit = "VND",
                        label = "",
                        date = localDate,
                    )
                )
            )
        }

        var labelError = requiredValidator(expense.info.label)

        val focusManager = LocalFocusManager.current
        val labelFocusRequester = remember { FocusRequester() }
        val amountFocusRequester = remember { FocusRequester() }

        LaunchedEffect(Unit) {
            labelFocusRequester.requestFocus()
        }

        Column {
            Row(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = CardDefaults.shape
                    )
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    FilledTextField(
                        modifier = Modifier.focusRequester(labelFocusRequester),
                        value = expense.info.label,
                        onValueChange = {
                            expense = expense.copy(
                                info = expense.info.copy(
                                    label = it
                                )
                            )
                        },
                        hint = stringResource(R.string.label_hint),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { amountFocusRequester.requestFocus() }
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FilledTextField(
                        modifier = Modifier.focusRequester(amountFocusRequester),
                        value = expense.info.amount.toString(),
                        onValueChange = {
                            expense = expense.copy(
                                info = expense.info.copy(
                                    amount = BigDecimal(it)
                                )
                            )
                        },
                        hint = stringResource(id = R.string.amount_hint),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.NumberPassword
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        )
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    BadgeChip(
                        label = expense.category.name,
                        color = expense.category.tint
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BadgeChip(
                        label = expense.sourceInfo.name,
                        color = expense.sourceInfo.tint
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                if (initialExpense != null) FilledTonalButton(
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onErrorContainer,
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ),
                    onClick = {
                        onDismissRequest()
                        onDelete(expense)
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
                    colors = ButtonDefaults.filledTonalButtonColors(
                        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(
                            alpha = 0.5f
                        ),
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    enabled = labelError == null,
                    onClick = {
                        onDismissRequest()
                        if (initialExpense != null) {
                            onUpdate(expense)
                        } else {
                            onSave(expense)
                        }
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