package com.devexperto.testingexpert.data.datasource

import com.devexperto.testingexpert.data.db.BoardDao
import com.devexperto.testingexpert.data.db.MoveEntity
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.move
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface BoardLocalDataSource {
    val board: Flow<TicTacToe>
    suspend fun saveMove(row: Int, column: Int)
    suspend fun reset()
}

class RoomBoardDataSource @Inject constructor(
    private val boardDao: BoardDao
) : BoardLocalDataSource {

    override val board: Flow<TicTacToe>
        get() = boardDao.getBoard().map { it.toTicTacToe() }

    override suspend fun saveMove(row: Int, column: Int) {
        boardDao.saveMove(MoveEntity(0, row, column))
    }

    override suspend fun reset() {
        boardDao.reset()
    }
}

private fun List<MoveEntity>.toTicTacToe(): TicTacToe {
    return fold(TicTacToe()) { acc, move ->
        acc.move(move.row, move.column)
    }
}