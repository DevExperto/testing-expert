package com.devexperto.testingexpert.di

import android.app.Application
import androidx.room.Room
import com.devexperto.architectcoders.di.ApiUrl
import com.devexperto.testingexpert.data.local.AppDatabase
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Singleton

interface TestAppExtrasModule {

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