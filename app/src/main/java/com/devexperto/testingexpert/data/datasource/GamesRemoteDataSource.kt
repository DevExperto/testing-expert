package com.devexperto.testingexpert.data.datasource

import com.devexperto.testingexpert.domain.VideoGame

interface GamesRemoteDataSource {
    suspend fun getGames(): List<VideoGame>
}