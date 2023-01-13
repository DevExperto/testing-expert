package com.devexperto.testingexpert.di

import android.app.Application
import androidx.room.Room
import com.devexperto.architectcoders.di.ApiUrl
import com.devexperto.testingexpert.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.StandardTestDispatcher
import javax.inject.Singleton


@OptIn(ExperimentalCoroutinesApi::class)
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppExtrasModule::class]
)
@Module
object TestAppExtrasModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.inMemoryDatabaseBuilder(
        app,
        AppDatabase::class.java
    )
        .setTransactionExecutor(StandardTestDispatcher().asExecutor())
        .build()

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "http://localhost:8080"

}