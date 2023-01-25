package com.devexperto.testingexpert.di

import android.app.Application
import androidx.compose.runtime.NoLiveLiterals
import androidx.room.Room
import com.devexperto.architectcoders.di.ApiUrl
import com.devexperto.testingexpert.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Singleton

/* This annotation is here because a bug when running tests with Hilt and Compose.
 * See https://stackoverflow.com/a/71189923 */
@NoLiveLiterals

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
        .setTransactionExecutor(Dispatchers.Main.asExecutor())
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "http://localhost:8080"

}