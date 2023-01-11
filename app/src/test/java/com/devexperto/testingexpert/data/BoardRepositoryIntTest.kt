package com.devexperto.testingexpert.data

import com.devexperto.testingexpert.data.datasource.BoardLocalDataSourceFake
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.move
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BoardRepositoryIntTest {

    @Test
    fun `when board is called, then return board from remote data source`() {
        val expectedBoard = TicTacToe().move(0, 0).move(1, 1)
        val localDataSource = BoardLocalDataSourceFake(expectedBoard)
        val boardRepository = BoardRepository(localDataSource)

        val board = runBlocking { boardRepository.board.first() }

        assertEquals(expectedBoard, board)
    }

    @Test
    fun `when move is called, then save move in local data source`() {
        val boardRepository = BoardRepository(BoardLocalDataSourceFake())

        runBlocking { boardRepository.move(0, 0) }

        val board = runBlocking { boardRepository.board.first() }

        assertEquals(TicTacToe().move(0, 0), board)
    }

    @Test
    fun `when reset is called, then reset board in local data source`() {
        val expectedBoard = TicTacToe().move(0, 0).move(1, 1)
        val boardRepository = BoardRepository(BoardLocalDataSourceFake(expectedBoard))

        runBlocking { boardRepository.reset() }

        assertEquals(TicTacToe(), runBlocking { boardRepository.board.first() })
    }

}