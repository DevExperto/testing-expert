package com.devexperto.testingexpert.data

import com.devexperto.testingexpert.data.datasource.BoardLocalDataSource
import com.devexperto.testingexpert.domain.TicTacToe
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BoardRepositoryTest {

    private val expectedBoard = TicTacToe()

    @Test
    fun `when board is called, then return board from remote data source`() {
        val localDataSource: BoardLocalDataSource = mockk {
            every { board } returns flowOf(expectedBoard)
        }
        val boardRepository = BoardRepository(localDataSource)

        val board = runBlocking { boardRepository.board.first() }

        assertEquals(expectedBoard, board)
    }

    @Test
    fun `when move is called, then save move in local data source`() {
        val localDataSource: BoardLocalDataSource = mockk {
            every { board } returns flowOf(expectedBoard)
            coJustRun { saveMove(any(), any()) }
        }
        val boardRepository = BoardRepository(localDataSource)

        runBlocking { boardRepository.move(0, 0) }

        coVerify { localDataSource.saveMove(0, 0) }
    }

    @Test
    fun `when reset is called, then reset board in local data source`() {
        val localDataSource: BoardLocalDataSource = mockk(relaxUnitFun = true) {
            every { board } returns flowOf(expectedBoard)
        }
        val boardRepository = BoardRepository(localDataSource)

        runBlocking { boardRepository.reset() }

        coVerify { localDataSource.reset() }
    }
}