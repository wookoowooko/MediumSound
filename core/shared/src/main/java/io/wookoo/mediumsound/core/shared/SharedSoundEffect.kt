package io.wookoo.mediumsound.core.shared

import io.wookoo.mediumsound.core.settings.Constants.SOUND_FOUR
import io.wookoo.mediumsound.core.settings.Constants.SOUND_ONE
import io.wookoo.mediumsound.core.settings.Constants.SOUND_THREE
import io.wookoo.mediumsound.core.settings.Constants.SOUND_TWO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

object SharedSoundEffect {
    interface SoundEffect {
        val soundName: String

        object One : SoundEffect {
            override val soundName: String
                get() = SOUND_ONE
        }

        object Two : SoundEffect {
            override val soundName: String
                get() = SOUND_TWO
        }
        object Three : SoundEffect {
            override val soundName: String
                get() = SOUND_THREE
        }
        object Four : SoundEffect {
            override val soundName: String
                get() = SOUND_FOUR
        }
    }

    private val _soundEffect by lazy { Channel<SoundEffect>() }
    val soundEffectFlow: Flow<SoundEffect> by lazy { _soundEffect.receiveAsFlow() }

    fun sendSoundEffectFlow(soundEffect: SoundEffect, isSoundEnabled: Boolean) {
        if (isSoundEnabled) {
            _soundEffect.trySend(soundEffect)
        }
    }

}