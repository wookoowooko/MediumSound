package io.wookoo.mediumsound.settings.screen

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsScreenModule = module {
    viewModelOf(::SettingsScreenViewModel)
}