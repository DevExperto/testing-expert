package com.devexperto.testingexpert.data.datasource

import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.move
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class BoardLocalDataSourceFake(ticTacToe: TicTacToe = TicTacToe()) : BoardLocalDataSource {
    private val inMemoryBoard = MutableStateFlow(ticTacToe)
    override val board = inMemoryBoard

    override suspend fun saveMove(row: Int, column: Int) {
        inMemoryBoard.update { it.move(row, column) }
    }

    override suspend fun reset() {
        inMemoryBoard.update { TicTacToe() }
    }
}