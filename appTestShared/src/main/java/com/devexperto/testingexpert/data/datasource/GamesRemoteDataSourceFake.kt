package com.devexperto.testingexpert.data.datasource

import com.devexperto.testingexpert.domain.VideoGame

class GamesRemoteDataSourceFake(
    var games: List<VideoGame> = emptyList()
) : GamesRemoteDataSource {

    override suspend fun getGames(): List<VideoGame> = games
}