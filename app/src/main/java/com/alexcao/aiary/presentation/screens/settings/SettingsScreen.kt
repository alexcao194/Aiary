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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alexcao.aiary.presentation.commons.AppHeader
import com.alexcao.aiary.presentation.screens.home.widgets.Badge
import com.alexcao.aiary.presentation.screens.settings.widgets.BadgeDialog
import com.alexcao.aiary.ui.theme.badgeLights
import com.alexcao.aiary.ui.theme.badges

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    var isBadgeDialogOpen by rememberSaveable { mutableStateOf(false) }
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
                    for (i in 0..5) {
                        Badge(
                            modifier = Modifier.padding(4.dp),
                            label = "Category $i",
                            color = badgeLights[i],
                            isLight = true,
                            onClick = { isBadgeDialogOpen = true }
                        )
                    }
                    Badge(
                        modifier = Modifier.padding(4.dp),
                        label = "+",
                        isLight = true,
                        color = badgeLights[6],
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = "Expense Source",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(8.dp))
                FlowRow {
                    for (i in 0..5) {
                        Badge(
                            modifier = Modifier.padding(4.dp),
                            label = "Source $i",
                            color = badges[i]
                        )
                    }
                    Badge(
                        modifier = Modifier.padding(4.dp),
                        label = "+",
                        color = badges[6],
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
                        Badge(
                            modifier = Modifier.padding(4.dp),
                            label = "VND",
                            color = badgeLights[i]
                        )
                    }
                    Badge(
                        modifier = Modifier.padding(4.dp),
                        label = "+",
                        color = badgeLights[6],
                    )
                }
            }
        }

        if (isBadgeDialogOpen) {
            BadgeDialog(
                onDismissRequest = { isBadgeDialogOpen = false },
                colors = badgeLights
            )
        }
    }
}