import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object NordColors {
    val PolarNight1 = Color(0xFF2E3440)
    val PolarNight2 = Color(0xFF3B4252)
    val PolarNight3 = Color(0xFF434C5E)
    val PolarNight4 = Color(0xFF4C566A)

    val Snow1 = Color(0xFFD8DEE9)
    val Snow2 = Color(0xFFE5E9F0)
    val Snow3 = Color(0xFFECEFF4)

    val Frost1 = Color(0xFF8FBCBB)
    val Frost2 = Color(0xFF88C0D0)
    val Frost3 = Color(0xFF81A1C1)
    val Frost4 = Color(0xFF5E81AC)

    val Aurora1 = Color(0xFFBF616A)
    val Aurora2 = Color(0xFFD08770)
    val Aurora3 = Color(0xFFEBCB8B)
    val Aurora4 = Color(0xFFA3BE8C)
    val Aurora5 = Color(0xFFB48EAD)
}

val LightPalette = lightColorScheme(
    primary = NordColors.PolarNight1,
    onPrimary = NordColors.Snow1,
    secondary = NordColors.PolarNight3,
    onSecondary = NordColors.Snow1,
    background = NordColors.Snow3,
    onBackground = NordColors.PolarNight1,
    surface = NordColors.Snow2,
    onSurface = Color.Black,
    error = NordColors.Aurora1,
    onError = Color.White,
    outline = NordColors.PolarNight4,
    tertiary = NordColors.PolarNight4,
    onTertiary = NordColors.Snow1
)

val DarkPalette = darkColorScheme(
    primary = NordColors.Frost3,
    onPrimary = NordColors.PolarNight1,
    secondary = NordColors.Aurora2,
    onSecondary = Color.Black,
    background = NordColors.PolarNight1,
    onBackground = Color.White,
    surface = NordColors.PolarNight2,
    onSurface = Color.White,
    error = NordColors.Aurora1,
    onError = Color.White,
    outline = NordColors.Aurora4,
    tertiary = NordColors.Frost4,
    onTertiary = NordColors.Snow2,
)
