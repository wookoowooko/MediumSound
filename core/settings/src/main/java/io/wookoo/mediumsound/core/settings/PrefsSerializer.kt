package io.wookoo.mediumsound.core.settings

import androidx.datastore.core.Serializer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import java.io.InputStream
import java.io.OutputStream

object PrefsSerializer : Serializer<SettingsModel>, KoinComponent {

    private val ioDispatcher: CoroutineDispatcher = getKoin().get(named("IO"))

    override val defaultValue: SettingsModel
        get() = SettingsModel()

    override suspend fun readFrom(input: InputStream): SettingsModel {
        return try {
            Json.decodeFromString(
                deserializer = SettingsModel.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            SettingsModel()
        }
    }

    override suspend fun writeTo(t: SettingsModel, output: OutputStream) {
        withContext(ioDispatcher) {
            output.write(
                Json.encodeToString(
                    serializer = SettingsModel.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}
