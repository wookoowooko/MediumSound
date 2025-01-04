package io.wookoo.mediumsound.core.audiomanager

import android.content.Context
import android.media.SoundPool
import io.wookoo.mediumsound.core.settings.Constants.SOUND_FOUR
import io.wookoo.mediumsound.core.settings.Constants.SOUND_ONE
import io.wookoo.mediumsound.core.settings.Constants.SOUND_THREE
import io.wookoo.mediumsound.core.settings.Constants.SOUND_TWO
import io.wookoo.mediumsound.core.settings.IDataStore
import kotlinx.coroutines.flow.first

class AppAudioManager(
    applicationContext: Context,
    private val dataStore: IDataStore,
) {

    private val soundPool: SoundPool = SoundPool.Builder().setMaxStreams(10).build()
    private val soundMap: MutableMap<String, Int> = mutableMapOf()

    init {
        soundMap[SOUND_ONE] = soundPool.load(applicationContext, R.raw.sound1, 1)
        soundMap[SOUND_TWO] = soundPool.load(applicationContext, R.raw.sound2, 1)
        soundMap[SOUND_THREE] = soundPool.load(applicationContext, R.raw.sound3, 1)
        soundMap[SOUND_FOUR] = soundPool.load(applicationContext, R.raw.sound4, 1)
    }

    suspend fun playSound(name: String) {
        val volume = getVolumeFromSettings()
        soundMap[name]?.let { soundId ->
            soundPool.play(soundId, volume, volume, 0, 0, 1f)
        }
    }

    private suspend fun getVolumeFromSettings(): Float {
        val volumeValue = dataStore.getSettings().first().soundVolume
        return volumeValue / 100f
    }

    fun release() {
        soundPool.release()
    }

}
