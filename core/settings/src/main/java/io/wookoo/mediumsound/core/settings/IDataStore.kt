package io.wookoo.mediumsound.core.settings

import kotlinx.coroutines.flow.Flow

interface IDataStore {
    fun getSettings(): Flow<SettingsModel>
    suspend fun updateSound(isSoundEnabled: Boolean)
    suspend fun updateSoundVolume(soundVolume: Float)
}