package com.devexperto.testingexpert.data

import com.devexperto.testingexpert.data.datasource.BoardLocalDataSource
import com.devexperto.testingexpert.domain.TicTacToe
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyBlocking

class BoardRepositoryTest {

    @Test
    fun `when board is called, then return board from remote data source`() {
        val expectedBoard = TicTacToe()
        val localDataSource: BoardLocalDataSource = mock {
            on { board } doReturn flowOf(expectedBoard)
        }
        val boardRepository = BoardRepository(localDataSource)

        val board = runBlocking { boardRepository.board.first() }

        assertEquals(expectedBoard, board)
    }

    @Test
    fun `when move is called, then save move in local data source`() {
        val localDataSource: BoardLocalDataSource = mock()
        val boardRepository = BoardRepository(localDataSource)

        runBlocking { boardRepository.move(0, 0) }

        verifyBlocking(localDataSource) { saveMove(0, 0) }
    }

    @Test
    fun `when reset is called, then reset board in local data source`() {
        val localDataSource: BoardLocalDataSource = mock()
        val boardRepository = BoardRepository(localDataSource)

        runBlocking { boardRepository.reset() }

        verifyBlocking(localDataSource) { reset() }
    }
}