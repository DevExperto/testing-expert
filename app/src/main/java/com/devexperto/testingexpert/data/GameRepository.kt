package com.devexperto.testingexpert.data

import com.devexperto.testingexpert.data.datasource.GameDataSource
import com.devexperto.testingexpert.domain.TicTacToe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepository @Inject constructor(private val localDataSource: GameDataSource) {
    val game: Flow<TicTacToe> = localDataSource.game

    suspend fun move(row: Int, column: Int) {
        localDataSource.saveMove(row, column)
    }

    suspend fun reset() {
        localDataSource.reset()
    }
}