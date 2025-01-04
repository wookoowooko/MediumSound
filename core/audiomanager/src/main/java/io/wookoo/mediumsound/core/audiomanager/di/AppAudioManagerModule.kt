package io.wookoo.mediumsound.core.audiomanager.di

import io.wookoo.mediumsound.core.audiomanager.AppAudioManager
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val appAudioManagerModule = module {
    factoryOf(::AppAudioManager)
}