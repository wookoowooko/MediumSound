package io.wookoo.mediumsound.first.screen

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

class FirstScreenViewModel(
    settingsRepository: IDataStore
) : ViewModel() {
    private val _uiState = MutableStateFlow(FirstScreenContract.UiState())

    val uiState = combine(
        _uiState,
        settingsRepository.getSettings()
    ) { uiState, settings ->
        uiState.copy(
            settings = settings
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000L), FirstScreenContract.UiState())

    private val isSoundEnabled: Boolean
        get() = uiState.value.settings.isSoundEnabled

    fun onIntent(intent: FirstScreenContract.OnIntent) {
        when (intent) {
            FirstScreenContract.OnIntent.OnFirstSoundClick -> {
                sendSoundEffectFlow(
                    soundEffect = SharedSoundEffect.SoundEffect.One,
                    isSoundEnabled = isSoundEnabled
                )
            }

            FirstScreenContract.OnIntent.OnSecondSoundClick -> {
                sendSoundEffectFlow(
                    soundEffect = SharedSoundEffect.SoundEffect.Two,
                    isSoundEnabled = isSoundEnabled
                )
            }
        }
    }
}


object FirstScreenContract {
    data class UiState(
        val settings: SettingsModel = SettingsModel()
    )

    sealed interface OnIntent {
        data object OnFirstSoundClick : OnIntent
        data object OnSecondSoundClick : OnIntent
    }
}