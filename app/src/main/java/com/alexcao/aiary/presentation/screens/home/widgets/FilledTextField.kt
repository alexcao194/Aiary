package com.alexcao.aiary.presentation.screens.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.alexcao.aiary.R
import com.alexcao.aiary.ui.theme.InterTypography
import com.alexcao.aiary.ui.theme.Primary
import com.alexcao.aiary.ui.theme.PrimaryBackground
import com.alexcao.aiary.ui.theme.Secondary
import com.alexcao.aiary.ui.theme.SecondaryBackground

@Composable
fun FilledTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier
            .background(
                color = SecondaryBackground.copy(alpha = 0.3f),
                shape = RoundedCornerShape(4.dp)
            )
            .fillMaxWidth()
            .height(28.dp),
        value = value,
        textStyle = InterTypography.bodySmall.copy(
            color = Secondary,
            fontWeight = FontWeight.Bold
        ),
        onValueChange = onValueChange,
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) Text(
                    text = hint,
                    style = InterTypography.bodySmall.copy(
                        color = Secondary
                    )
                )
                innerTextField()
            }
        },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions
    )
}