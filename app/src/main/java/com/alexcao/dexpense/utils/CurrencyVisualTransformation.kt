import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.core.text.isDigitsOnly
import com.alexcao.dexpense.utils.CurrencyOffsetMapping
import com.alexcao.dexpense.utils.extensions.toCurrency

private class CurrencyVisualTransformation(
    private val unit: String,
    private val isNegative: Boolean
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text.trim()
        if (originalText.isEmpty()) {
            return TransformedText(text, OffsetMapping.Identity)
        }
        if (originalText.isDigitsOnly().not()) {
            return TransformedText(text, OffsetMapping.Identity)
        }
        val formattedText = originalText.toCurrency(unit, isNegative = isNegative)
        return TransformedText(
            AnnotatedString(formattedText),
            CurrencyOffsetMapping(originalText, formattedText)
        )
    }
}

@Composable
fun rememberCurrencyVisualTransformation(
    currency: String,
    isNegative: Boolean = false,
): VisualTransformation {
    return remember(currency, isNegative) {
        CurrencyVisualTransformation(currency, isNegative)
    }
}