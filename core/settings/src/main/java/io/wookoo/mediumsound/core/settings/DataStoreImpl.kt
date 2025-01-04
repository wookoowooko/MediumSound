package io.wookoo.mediumsound.core.settings

import android.content.Context
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

private val Context.dataStore by dataStore("settings.json", PrefsSerializer)

class DataStoreImpl(
    private val context: Context
):IDataStore  {
    override fun getSettings(): Flow<SettingsModel> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(SettingsModel())
                } else {
                    throw exception
                }
            }
    }

    override suspend fun updateSound(isSoundEnabled: Boolean) {
        context.dataStore.updateData { settings ->
            settings.copy(isSoundEnabled = isSoundEnabled)
        }
    }

    override suspend fun updateSoundVolume(soundVolume: Float) {
        context.dataStore.updateData { settings ->
            settings.copy(soundVolume = soundVolume)
        }
    }
}
