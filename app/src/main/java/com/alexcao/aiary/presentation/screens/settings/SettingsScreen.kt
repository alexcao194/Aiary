package com.alexcao.aiary.presentation.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alexcao.aiary.presentation.commons.AppHeader
import com.alexcao.aiary.presentation.screens.home.widgets.Badge
import com.alexcao.aiary.ui.theme.InterTypography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
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
                    style = InterTypography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.padding(8.dp))
                FlowRow {
                    for (i in 0..5) {
                        Badge(
                            modifier = Modifier.padding(4.dp),
                            label = "Category $i"
                        )
                    }
                    Badge(
                        modifier = Modifier.padding(4.dp),
                        label = "+"
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = "Expense Source",
                    style = InterTypography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.padding(8.dp))
                FlowRow {
                    for (i in 0..5) {
                        Badge(
                            modifier = Modifier.padding(4.dp),
                            label = "Source $i"
                        )
                    }
                    Badge(
                        modifier = Modifier.padding(4.dp),
                        label = "+"
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = "Unit",
                    style = InterTypography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.padding(8.dp))
                FlowRow {
                    for (i in 0..5) {
                        Badge(
                            modifier = Modifier.padding(4.dp),
                            label = "VND"
                        )
                    }
                    Badge(
                        modifier = Modifier.padding(4.dp),
                        label = "+"
                    )
                }
            }
        }
    }
}