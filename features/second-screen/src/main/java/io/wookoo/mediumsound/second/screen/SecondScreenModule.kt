package io.wookoo.mediumsound.second.screen

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val secondScreenModule = module {
    viewModelOf(::SecondScreenViewModel)
}
