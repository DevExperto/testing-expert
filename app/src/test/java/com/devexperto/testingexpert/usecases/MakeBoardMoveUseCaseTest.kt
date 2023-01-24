package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.BoardRepository
import com.devexperto.testingexpert.domain.TicTacToe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MakeBoardMoveUseCaseTest {

    @Test
    fun `when invoke is called, then call repository move`() {
        val repository: BoardRepository = mockk(relaxUnitFun = true) {
            coEvery { board } returns flowOf(TicTacToe())
        }
        val useCase = MakeBoardMoveUseCase(repository, mockk())

        runBlocking { useCase(0, 0) }

        coVerify { repository.move(0, 0) }
    }
}