package com.alexcao.dexpense.presentation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alexcao.dexpense.R
import com.alexcao.dexpense.data.models.Category
import com.alexcao.dexpense.data.models.ExpenseType
import com.alexcao.dexpense.data.models.Source
import com.alexcao.dexpense.presentation.commons.AppHeader
import com.alexcao.dexpense.presentation.commons.BadgeChip
import com.alexcao.dexpense.presentation.screens.settings.widgets.CategoryDialog
import com.alexcao.dexpense.presentation.screens.settings.widgets.SourceDialog
import com.alexcao.dexpense.presentation.screens.settings.widgets.SourceItem
import com.alexcao.dexpense.ui.theme.badgeOther

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    navHostController: NavHostController,
    message: String? = null
) {
    val state = settingsViewModel.state.collectAsState().value
    val categories = state.categories
    val expenseCategories = categories.filter { it.type == ExpenseType.EXPENSE }
    val incomeCategories = categories.filter { it.type == ExpenseType.INCOME }
    var expenseType = ExpenseType.EXPENSE
    val sources = state.sources
    val error = state.error

    val snackbarHostState = remember { SnackbarHostState() }
    var isCategoryDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isSourceDialogOpen by rememberSaveable { mutableStateOf(false) }
    var currentCategory: Category? = null
    var currentSource: Source? = null

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            AppHeader(
                navHostController = navHostController
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.expense_category),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(8.dp))
                FlowRow {
                    for (category in expenseCategories) {
                        BadgeChip(
                            modifier = Modifier.padding(4.dp),
                            label = category.name,
                            color = category.tint,
                            onClick = {
                                isCategoryDialogOpen = true
                                currentCategory = category
                            }
                        )
                    }
                    BadgeChip(
                        modifier = Modifier.padding(4.dp),
                        label = "+",
                        color = Color.Gray.copy(alpha = 0.5f),
                        onClick = {
                            isCategoryDialogOpen = true
                            currentCategory = null
                            expenseType = ExpenseType.EXPENSE
                        }
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = stringResource(R.string.income_category),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(8.dp))
                FlowRow {
                    for (category in incomeCategories) {
                        BadgeChip(
                            modifier = Modifier.padding(4.dp),
                            label = category.name,
                            color = category.tint,
                            onClick = {
                                isCategoryDialogOpen = true
                                currentCategory = category
                            }
                        )
                    }
                    BadgeChip(
                        modifier = Modifier.padding(4.dp),
                        label = "+",
                        color = Color.Gray.copy(alpha = 0.5f),
                        onClick = {
                            isCategoryDialogOpen = true
                            currentCategory = null
                            expenseType = ExpenseType.INCOME
                        }
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = stringResource(R.string.expense_source),
                    style = MaterialTheme.typography.titleMedium
                )
                LazyColumn {
                    items(sources) { source ->
                        SourceItem(
                            modifier = Modifier
                                .padding(vertical = 8.dp),
                            source = source,
                            onClick = {
                                isSourceDialogOpen = true
                                currentSource = source
                            }
                        )
                    }
                }
                Button(
                    modifier = modifier
                        .padding(top = 8.dp)
                        .height(60.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Gray.copy(alpha = 0.5f),
                    ),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    onClick = {
                        isSourceDialogOpen = true
                        currentSource = null
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add source button",
                        tint = Color.White
                    )
                }
            }
        }

        if (isCategoryDialogOpen) {
            CategoryDialog(
                initialCategory = currentCategory,
                onDismissRequest = { isCategoryDialogOpen = false },
                onSave = { category -> settingsViewModel.saveCategory(category) },
                onUpdate = { category -> settingsViewModel.updateCategory(category) },
                onDelete = { category -> settingsViewModel.deleteCategory(category) },
                expenseType = expenseType
            )
        }

        if (isSourceDialogOpen) {
            SourceDialog(
                initialSource = currentSource,
                onDismissRequest = { isSourceDialogOpen = false },
                onSave = { source -> settingsViewModel.saveSource(source) },
                onUpdate = { source -> settingsViewModel.updateSource(source) },
                onDelete = { source -> settingsViewModel.deleteSource(source) }
            )
        }

        LaunchedEffect(error) {
            error?.let {
                snackbarHostState.showSnackbar(it)
                settingsViewModel.clearError()
            }
        }

        LaunchedEffect(message) {
            message?.let {
                snackbarHostState.showSnackbar(it)
            }
        }
    }
}