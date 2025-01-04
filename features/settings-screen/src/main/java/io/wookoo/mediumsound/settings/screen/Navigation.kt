package io.wookoo.mediumsound.settings.screen


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object SettingsScreenRoute

fun NavGraphBuilder.settingsScreen() {
    composable<SettingsScreenRoute> {
        SettingsScreen()
    }
}
