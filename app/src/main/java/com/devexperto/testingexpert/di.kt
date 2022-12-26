package com.devexperto.testingexpert

import android.app.Application
import androidx.room.Room
import com.devexperto.testingexpert.data.datasource.GameDataSource
import com.devexperto.testingexpert.data.datasource.RoomGameDataSource
import com.devexperto.testingexpert.data.db.AppDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "app-db"
    ).build()

    @Provides
    @Singleton
    fun provideGameDao(db: AppDatabase) = db.gameDao

}

@InstallIn(SingletonComponent::class)
@Module
abstract class AppDataModule {

    @Binds
    abstract fun bindGameDataSource(impl: RoomGameDataSource): GameDataSource

}