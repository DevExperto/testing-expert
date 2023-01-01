package com.devexperto.testingexpert.di

import android.app.Application
import androidx.room.Room
import com.devexperto.architectcoders.di.ApiKey
import com.devexperto.architectcoders.di.ApiUrl
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.data.datasource.*
import com.devexperto.testingexpert.data.local.AppDatabase
import com.devexperto.testingexpert.data.remote.GamesRetrofitDataSource
import com.devexperto.testingexpert.data.remote.GamesService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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
    fun provideBoardDao(db: AppDatabase) = db.boardDao

    @Provides
    @Singleton
    fun provideScoreDao(db: AppDatabase) = db.scoreDao

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "https://api.rawg.io/api/"

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Provides
    @Singleton
    fun provideRemoteService(@ApiUrl apiUrl: String, okHttpClient: OkHttpClient): GamesService {

        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

}

@InstallIn(SingletonComponent::class)
@Module
abstract class AppDataModule {

    @Binds
    abstract fun bindBoardDataSource(impl: RoomBoardDataSource): BoardLocalDataSource

    @Binds
    abstract fun bindScoreDataSource(impl: RoomScoreDataSource): ScoreLocalDataSource

    @Binds
    abstract fun bindGameDataSource(impl: GamesRetrofitDataSource): GamesRemoteDataSource

}