package io.wookoo.mediumsound.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.wookoo.mediumsound.first.screen.FirstScreenRoute
import io.wookoo.mediumsound.first.screen.firstScreen
import io.wookoo.mediumsound.second.screen.SecondScreenRoute
import io.wookoo.mediumsound.second.screen.secondScreen
import io.wookoo.mediumsound.settings.screen.SettingsScreenRoute
import io.wookoo.mediumsound.settings.screen.settingsScreen

@Composable
internal fun CustomNavHost(
    navigator: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navigator,
        modifier = modifier,
        startDestination = FirstScreenRoute
    ) {
        firstScreen()
        secondScreen()
        settingsScreen()
    }
}


internal data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

internal val topLevelRoutes = listOf(
    TopLevelRoute("First", FirstScreenRoute, Icons.Default.Home),
    TopLevelRoute("Second", SecondScreenRoute, Icons.Default.Favorite),
    TopLevelRoute("Settings", SettingsScreenRoute, Icons.Default.Settings),
)