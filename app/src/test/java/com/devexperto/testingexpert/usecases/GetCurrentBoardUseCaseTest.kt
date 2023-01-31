package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.BoardRepository
import com.devexperto.testingexpert.domain.TicTacToe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GetCurrentBoardUseCaseTest {

    @Test
    fun `when invoke is called, then return board from repository`() {
        val expectedBoard = TicTacToe()
        val repository: BoardRepository = mockk() {
            every { board } returns flowOf(expectedBoard)
        }
        val useCase = GetCurrentBoardUseCase(repository)

        val board = runBlocking { useCase().first() }

        assertEquals(expectedBoard, board)
    }
}