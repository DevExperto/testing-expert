package com.devexperto.testingexpert.data.datasource

import com.devexperto.testingexpert.data.db.GameDao
import com.devexperto.testingexpert.data.db.MoveEntity
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.move
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GameDataSource {
    val game: Flow<TicTacToe>
    suspend fun saveMove(row: Int, column: Int)
    suspend fun reset()
}

class RoomGameDataSource @Inject constructor(
    private val gameDao: GameDao
) : GameDataSource {

    override val game: Flow<TicTacToe>
        get() = gameDao.getGame().map { it.toTicTacToe() }

    override suspend fun saveMove(row: Int, column: Int) {
        gameDao.saveMove(MoveEntity(0, row, column))
    }

    override suspend fun reset() {
        gameDao.reset()
    }
}

private fun List<MoveEntity>.toTicTacToe(): TicTacToe {
    return fold(TicTacToe()) { acc, move ->
        acc.move(move.row, move.column)
    }
}