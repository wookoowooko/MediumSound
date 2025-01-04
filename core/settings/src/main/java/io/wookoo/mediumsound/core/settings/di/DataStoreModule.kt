package io.wookoo.mediumsound.core.settings.di

import io.wookoo.mediumsound.core.settings.DataStoreImpl
import io.wookoo.mediumsound.core.settings.IDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val dataStoreModule = module {
    singleOf(::DataStoreImpl).bind(IDataStore::class)
    single<CoroutineDispatcher>(named("IO")) { Dispatchers.IO }
}