package io.wookoo.mediumsound.core.settings

import kotlinx.serialization.Serializable

@Serializable
data class SettingsModel (
    val soundVolume:Float = 80f,
    val isSoundEnabled:Boolean = true
)