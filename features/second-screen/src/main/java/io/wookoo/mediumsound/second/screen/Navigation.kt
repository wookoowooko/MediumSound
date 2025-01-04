package io.wookoo.mediumsound.second.screen


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object SecondScreenRoute

fun NavGraphBuilder.secondScreen() {
    composable<SecondScreenRoute> {
        SecondScreen()
    }
}
