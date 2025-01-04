package io.wookoo.mediumsound.settings.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.wookoo.mediumsound.core.settings.IDataStore
import io.wookoo.mediumsound.core.settings.SettingsModel
import io.wookoo.mediumsound.core.shared.SharedSoundEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsScreenViewModel(
    private val dataStore: IDataStore
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsScreenContract.UiState())
    val uiState = combine(
        _uiState,
        dataStore.getSettings()
    ) { uiState, settings ->
        uiState.copy(
            settings = settings
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SettingsScreenContract.UiState()
    )

    fun onIntent(intent: SettingsScreenContract.OnIntent) {
        when (intent) {
            is SettingsScreenContract.OnIntent.OnSoundSwitchClick -> {
                viewModelScope.launch {
                    dataStore.updateSound(intent.isSoundEnabled)
                    sendSound()
                }
            }

            is SettingsScreenContract.OnIntent.OnSoundVolumeChange -> {
                viewModelScope.launch {
                    dataStore.updateSoundVolume(intent.volume)
                    sendSound()
                }
            }
        }
    }

    private fun sendSound() {
        SharedSoundEffect.sendSoundEffectFlow(
            isSoundEnabled = uiState.value.settings.isSoundEnabled,
            soundEffect = SharedSoundEffect.SoundEffect.One
        )
    }
}

data object SettingsScreenContract {
    data class UiState(
        val settings: SettingsModel = SettingsModel()
    )

    sealed interface OnIntent {
        data class OnSoundSwitchClick(val isSoundEnabled: Boolean) : OnIntent
        data class OnSoundVolumeChange(val volume: Float) : OnIntent
    }
}