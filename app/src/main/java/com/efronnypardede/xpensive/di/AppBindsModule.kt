package com.efronnypardede.xpensive.di

import com.efronnypardede.xpensive.data.repository.XpenseSourceDataRepository
import com.efronnypardede.xpensive.data.repository.XpenseSourceRepository
import com.efronnypardede.xpensive.data.source.XpenseSourceDataSource
import com.efronnypardede.xpensive.data.source.local.XpenseSourceLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppBindsModule {
    @Binds
    @Singleton
    fun provideXpenseSourceLocalDataSource(
        localDataSource: XpenseSourceLocalDataSource
    ): XpenseSourceDataSource

    @Binds
    @Singleton
    fun provideXpenseSourceRepository(
        repository: XpenseSourceDataRepository
    ): XpenseSourceRepository
}