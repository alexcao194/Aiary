package com.alexcao.aiary.presentation.screens.home.widgets

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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.alexcao.aiary.R
import com.alexcao.aiary.data.models.Expense
import com.alexcao.aiary.ui.theme.InterTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDialog(
    modifier: Modifier = Modifier,
    expense: Expense? = null,
    onDismissRequest: () -> Unit,
    onSave: (Expense) -> Unit,
) {
    BasicAlertDialog(
        modifier = modifier.fillMaxWidth(),
        onDismissRequest = { onDismissRequest() }
    ) {
        var label by rememberSaveable { mutableStateOf(expense?.info?.label ?: "") }
        var amount by rememberSaveable { mutableStateOf(expense?.info?.amount?.toString() ?: "") }
        val focusManager = LocalFocusManager.current
        val labelFocusRequester = remember { FocusRequester() }
        val amountFocusRequester = remember { FocusRequester() }

        LaunchedEffect(Unit) {
            labelFocusRequester.requestFocus()
        }

        Column {
            Card{
                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        FilledTextField(
                            modifier = Modifier.focusRequester(labelFocusRequester),
                            value = label,
                            onValueChange = { label = it },
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
                            value = amount,
                            onValueChange = { amount = it },
                            hint = stringResource(id = R.string.amount_hint),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
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
                        Badge(label = "Transport")
                        Spacer(modifier = Modifier.height(8.dp))
                        Badge(label = "Cash")
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                FilledTonalButton(onClick = { onDismissRequest() }) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        style = InterTypography.bodySmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                FilledTonalButton(onClick = { onDismissRequest() }) {
                    Text(
                        text = stringResource(id = R.string.save),
                        style = InterTypography.bodySmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}