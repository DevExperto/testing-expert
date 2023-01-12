package com.devexperto.testingexpert.data

import com.devexperto.testingexpert.data.datasource.GamesRemoteDataSource
import com.devexperto.testingexpert.domain.VideoGame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GamesRepository @Inject constructor(private val gamesRemoteDataSource: GamesRemoteDataSource) {

    val games: Flow<List<VideoGame>> get() = flow { emit(gamesRemoteDataSource.getGames()) }
}