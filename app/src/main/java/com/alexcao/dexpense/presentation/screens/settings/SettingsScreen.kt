package com.alexcao.dexpense.presentation.screens.settings

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alexcao.dexpense.data.models.ExpenseCategory
import com.alexcao.dexpense.data.models.ExpenseSource
import com.alexcao.dexpense.presentation.commons.AppHeader
import com.alexcao.dexpense.presentation.screens.home.widgets.BadgeChip
import com.alexcao.dexpense.presentation.screens.settings.widgets.CategoryDialog
import com.alexcao.dexpense.presentation.screens.settings.widgets.SourceDialog
import com.alexcao.dexpense.ui.theme.badgeLights
import com.alexcao.dexpense.ui.theme.badgeOther
import com.alexcao.dexpense.ui.theme.badgeOtherLight

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val state = settingsViewModel.state.collectAsState().value
    val categories = state.categories
    val sources = state.sources
    val error = state.error

    val snackbarHostState = remember { SnackbarHostState() }
    var isCategoryDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isSourceDialogOpen by rememberSaveable { mutableStateOf(false) }
    var currentCategory: ExpenseCategory? = null
    var currentSource: ExpenseSource? = null

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
                    text = "Expense Category",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(8.dp))
                FlowRow {
                    for (category in categories) {
                        BadgeChip(
                            modifier = Modifier.padding(4.dp),
                            label = category.name,
                            color = category.tint,
                            isLight = true,
                            onClick = {
                                isCategoryDialogOpen = true
                                currentCategory = category
                            }
                        )
                    }
                    BadgeChip(
                        modifier = Modifier.padding(4.dp),
                        label = "+",
                        color = badgeOtherLight,
                        isLight = true,
                        onClick = {
                            isCategoryDialogOpen = true
                            currentCategory = null
                        }
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = "Expense Source",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(8.dp))
                FlowRow {
                    for (source in sources) {
                        BadgeChip(
                            modifier = Modifier.padding(4.dp),
                            label = source.name,
                            color = source.tint,
                            onClick = {
                                isSourceDialogOpen = true
                                currentSource = source
                            }
                        )
                    }
                    BadgeChip(
                        modifier = Modifier.padding(4.dp),
                        label = "+",
                        color = badgeOther,
                        onClick = {
                            isSourceDialogOpen = true
                            currentSource = null
                        }
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = "Unit",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(8.dp))
                FlowRow {
                    for (i in 0..5) {
                        BadgeChip(
                            modifier = Modifier.padding(4.dp),
                            label = "VND",
                            color = badgeLights[i]
                        )
                    }
                    BadgeChip(
                        modifier = Modifier.padding(4.dp),
                        label = "+",
                        color = badgeLights[6],
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
                onDelete = { category -> settingsViewModel.deleteCategory(category) }
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
                Log.d("TAG", "SettingsScreen: $it")
                snackbarHostState.showSnackbar(it)
                settingsViewModel.clearError()
            }
        }
    }
}