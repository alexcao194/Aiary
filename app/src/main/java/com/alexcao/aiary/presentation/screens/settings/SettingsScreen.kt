package com.alexcao.aiary.presentation.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alexcao.aiary.presentation.commons.AppHeader
import com.alexcao.aiary.presentation.screens.home.widgets.BadgeChip
import com.alexcao.aiary.presentation.screens.settings.widgets.BadgeDialog
import com.alexcao.aiary.ui.theme.badgeLights
import com.alexcao.aiary.ui.theme.badgeOther
import com.alexcao.aiary.ui.theme.badgeOtherLight
import com.alexcao.aiary.ui.theme.badges

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

    var isBadgeDialogOpen by rememberSaveable { mutableStateOf(false) }
    var currentLabel = rememberSaveable { mutableStateOf("") }
    var currentColor = rememberSaveable { mutableStateOf(badgeLights.first().toArgb()) }
    var isLight = rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = modifier
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
                                currentLabel.value = category.name
                                currentColor.value = category.tint.toArgb()
                                isBadgeDialogOpen = true
                                isLight.value = true
                            }
                        )
                    }
                    BadgeChip(
                        modifier = Modifier.padding(4.dp),
                        label = "+",
                        color = badgeOtherLight,
                        isLight = true,
                        onClick = {
                            isBadgeDialogOpen = true
                            currentLabel.value = ""
                            currentColor.value = badgeLights[0].toArgb()
                            isLight.value = true
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
                                isBadgeDialogOpen = true
                                currentLabel.value = source.name
                                currentColor.value = source.tint.toArgb()
                                isLight.value = false
                            }
                        )
                    }
                    BadgeChip(
                        modifier = Modifier.padding(4.dp),
                        label = "+",
                        color = badgeOther,
                        onClick = {
                            isBadgeDialogOpen = true
                            currentLabel.value = ""
                            currentColor.value = badges[0].toArgb()
                            isLight.value = false
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

        if (isBadgeDialogOpen) {
            BadgeDialog(
                onDismissRequest = {
                    isBadgeDialogOpen = false },
                colors = if (isLight.value) badgeLights else badges,
                label = currentLabel.value,
                color = Color(currentColor.value),
                onChange = { label, color ->
                    currentLabel.value = label
                    currentColor.value = color.toArgb()
                },
                onSave = {
                    settingsViewModel.onSaveCategory(
                        currentLabel.value,
                        Color(currentColor.value)
                    )
                }
            )
        }
    }
}