package com.devexperto.testingexpert.di

import com.devexperto.testingexpert.data.datasource.*
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppDataModule::class]
)
@Module
object TestAppDataModule {

    @Provides
    @Singleton
    fun provideBoardDataSourceFake(): BoardLocalDataSourceFake = BoardLocalDataSourceFake()

    @Provides
    @Singleton
    fun provideScoreDataSourceFake(): ScoreLocalDataSourceFake = ScoreLocalDataSourceFake()

    @Provides
    @Singleton
    fun provideGameRemoteSourceFake(): GamesRemoteDataSourceFake = GamesRemoteDataSourceFake()

    @Provides
    fun provideBoardDataSource(fake: BoardLocalDataSourceFake): BoardLocalDataSource = fake

    @Provides
    fun provideScoreDataSource(fake: ScoreLocalDataSourceFake): ScoreLocalDataSource = fake

    @Provides
    fun provideGameDataSource(fake: GamesRemoteDataSourceFake): GamesRemoteDataSource = fake

}