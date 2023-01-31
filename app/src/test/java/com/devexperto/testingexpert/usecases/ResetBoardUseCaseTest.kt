package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.BoardRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class ResetBoardUseCaseTest {

    @Test
    fun `when invoke is called, then call repository reset`() {
        val repository: BoardRepository = mockk(relaxUnitFun = true)
        val useCase = ResetBoardUseCase(repository)

        runBlocking { useCase() }

        coVerify { repository.reset() }
    }
}