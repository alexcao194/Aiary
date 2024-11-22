package com.alexcao.dexpense.presentation.screens.settings.widgets

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.alexcao.dexpense.R
import com.alexcao.dexpense.data.models.Source
import com.alexcao.dexpense.data.models.SourceAmount
import com.alexcao.dexpense.data.models.SourceInfo
import com.alexcao.dexpense.presentation.commons.FilledTextField
import com.alexcao.dexpense.ui.theme.badgeColors
import rememberCurrencyVisualTransformation
import java.math.BigDecimal
import java.math.BigInteger

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SourceDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onSave: (Source) -> Unit,
    onUpdate: (Source) -> Unit,
    onDelete: (Source) -> Unit,
    initialSource: Source? = null
) {
    var source by remember {
        mutableStateOf(
            initialSource ?: Source(
                info = SourceInfo(
                    tint = badgeColors.first()
                ),
                amount = SourceAmount()
            )
        )
    }

    BasicAlertDialog(
        modifier = modifier.fillMaxWidth(),
        onDismissRequest = { onDismissRequest() }
    ) {
        val labelFocusRequester = remember { FocusRequester() }
        val amountFocusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current
        val currencyVisualTransformation = rememberCurrencyVisualTransformation(currency = "USD")

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
                    value = source.info.name,
                    onValueChange = {
                        source = source.copy(
                            info = source.info.copy(
                                name = it
                            )
                        )
                    },
                    hint = stringResource(R.string.label_hint),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            amountFocusRequester.requestFocus()
                        }
                    ),
                )
                Spacer(modifier = Modifier.height(16.dp))
                FilledTextField(
                    value = source.amount.amount.toString(),
                    onValueChange = { value ->
                        val trimmed = value.trimStart('0').trim { it.isDigit().not() }
                        if (trimmed.isNotBlank()) {
                            source = source.copy(
                                amount = source.amount.copy(
                                    amount = BigInteger(trimmed)
                                )
                            )
                        }
                    },
                    hint = stringResource(R.string.amount_hint),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                    visualTransformation = currencyVisualTransformation
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow {
                    items(badgeColors) { badgeColor ->
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .clip(CircleShape)
                                .background(color = badgeColor)
                                .size(32.dp)
                                .clickable {
                                    source = source.copy(
                                        info = source.info.copy(
                                            tint = badgeColor
                                        )
                                    )
                                },

                            contentAlignment = Alignment.Center
                        ) {
                            if (badgeColor.toArgb() == source.info.tint.toArgb()) {
                                Icon(
                                    imageVector = Icons.Rounded.Check,
                                    contentDescription = "Selected",
                                    tint = Color.White
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
                Spacer(modifier = Modifier.weight(1f))
                if (initialSource != null) FilledTonalButton(
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onErrorContainer,
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ),
                    onClick = {
                        onDismissRequest()
                        onDelete(source)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.delete),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                FilledTonalButton(
                    colors = ButtonDefaults.textButtonColors(
                        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(
                            alpha = 0.5f
                        ),
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    enabled = source.info.name.isNotBlank(),
                    onClick = {
                        onDismissRequest()
                        if (initialSource != null)
                            onUpdate(source)
                        else
                            onSave(source)
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