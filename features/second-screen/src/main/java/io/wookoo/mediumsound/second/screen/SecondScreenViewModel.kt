package io.wookoo.mediumsound.second.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.wookoo.mediumsound.core.settings.IDataStore
import io.wookoo.mediumsound.core.settings.SettingsModel
import io.wookoo.mediumsound.core.shared.SharedSoundEffect
import io.wookoo.mediumsound.core.shared.SharedSoundEffect.sendSoundEffectFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SecondScreenViewModel(
    settingsRepository: IDataStore
) : ViewModel() {
    private val _uiState = MutableStateFlow(SecondScreenContract.UiState())

    val uiState = combine(
        _uiState,
        settingsRepository.getSettings()
    ) { uiState, settings ->
        uiState.copy(
            settings = settings
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000L), SecondScreenContract.UiState())

    fun onIntent(intent: SecondScreenContract.OnIntent) {
        when (intent) {
            SecondScreenContract.OnIntent.OnThirdSoundClick -> {
                sendSoundEffectFlow(
                    soundEffect = SharedSoundEffect.SoundEffect.Three,
                    isSoundEnabled = uiState.value.settings.isSoundEnabled
                )
            }

            SecondScreenContract.OnIntent.OnFourthSoundClick -> {
                sendSoundEffectFlow(
                    soundEffect = SharedSoundEffect.SoundEffect.Four,
                    isSoundEnabled = uiState.value.settings.isSoundEnabled
                )
            }
        }
    }
}


object SecondScreenContract {
    data class UiState(
        val settings: SettingsModel = SettingsModel()
    )

    sealed interface OnIntent {
        data object OnThirdSoundClick : OnIntent
        data object OnFourthSoundClick : OnIntent
    }
}