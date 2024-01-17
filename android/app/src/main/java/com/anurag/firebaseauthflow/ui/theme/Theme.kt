import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun FirebaseAuthFlowTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightPalette
    } else {
        DarkPalette
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
