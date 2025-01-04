package io.wookoo.mediumsound.first.screen

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val firstScreenModule = module {
    viewModelOf(::FirstScreenViewModel)
}