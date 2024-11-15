package com.alexcao.aiary.presentation.screens.home.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexcao.aiary.R
import com.alexcao.aiary.ui.theme.InterTypography
import com.alexcao.aiary.ui.theme.Primary
import com.alexcao.aiary.ui.theme.PrimaryBackground

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    onChangeMonth: (Int) -> Unit = {},
    onOpenDatePicker: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = PrimaryBackground,
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyRow(
                modifier = Modifier.weight(1f)
            ) {
                items(12) { index ->
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(
                            text = "November",
                            style = if (index == 0) InterTypography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            ) else InterTypography.titleMedium.copy(
                                color = Color.Gray
                            ),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            Image(
                painter = painterResource(id = R.drawable.calendar),
                contentDescription = "Button to open date picker",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clip(CircleShape)
                    .clickable(
                        onClick = { },
                    )
                    .background(Color.Transparent)
                    .border(1.dp, Primary, CircleShape)
                    .padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun HomeHeaderPreview() {
    HomeHeader()
}