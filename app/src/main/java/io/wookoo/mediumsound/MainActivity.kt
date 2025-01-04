package io.wookoo.mediumsound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.wookoo.mediumsound.core.audiomanager.AppAudioManager
import io.wookoo.mediumsound.core.settings.IDataStore
import io.wookoo.mediumsound.core.shared.SharedSoundEffect
import io.wookoo.mediumsound.core.shared.SharedSoundEffect.soundEffectFlow
import io.wookoo.mediumsound.navigation.CustomNavHost
import io.wookoo.mediumsound.navigation.topLevelRoutes
import io.wookoo.mediumsound.ui.theme.MediumSoundTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {

    private val audioManager: AppAudioManager = get()
    private val settings: IDataStore by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            KoinAndroidContext {
                val observer = LocalLifecycleOwner.current
                val navController = rememberNavController()
                MediumSoundTheme {
                    Scaffold(modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            NavigationBar {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                topLevelRoutes.forEach { topLevelRoute ->
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                imageVector = topLevelRoute.icon,
                                                contentDescription = topLevelRoute.name
                                            )
                                        },
                                        label = { Text(topLevelRoute.name) },
                                        selected = currentDestination?.hierarchy?.any {
                                            it.hasRoute(
                                                topLevelRoute.route::class
                                            )
                                        } == true,
                                        onClick = {
                                            lifecycleScope.launch {
                                                SharedSoundEffect.sendSoundEffectFlow(
                                                    SharedSoundEffect.SoundEffect.Four,
                                                    isSoundEnabled = settings.getSettings().first().isSoundEnabled
                                                )
                                            }
                                            navController.navigate(topLevelRoute.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }) { innerPadding ->
                        CustomNavHost(navController, Modifier.padding(innerPadding))
                    }
                }

                LaunchedEffect(Unit) {
                    observer.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        soundEffectFlow.collectLatest { soundEffect ->
                            audioManager.playSound(soundEffect.soundName)
                        }
                    }
                }
                DisposableEffect(Unit) {
                    onDispose {
                        audioManager.release()
                    }
                }
            }
        }
    }
}



