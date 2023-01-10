package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.BoardRepository
import com.devexperto.testingexpert.domain.TicTacToe
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetCurrentBoardUseCaseTest {

    @Test
    fun `when invoke is called, then return board from repository`() {
        val expectedBoard = TicTacToe()
        val repository: BoardRepository = mock() {
            onBlocking { board } doReturn flowOf(expectedBoard)
        }
        val useCase = GetCurrentBoardUseCase(repository)

        val board = runBlocking { useCase().first() }

        assertEquals(expectedBoard, board)
    }
}