package io.wookoo.mediumsound.first.screen


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object FirstScreenRoute

fun NavGraphBuilder.firstScreen() {
    composable<FirstScreenRoute> {
        FirstScreen()
    }
}
