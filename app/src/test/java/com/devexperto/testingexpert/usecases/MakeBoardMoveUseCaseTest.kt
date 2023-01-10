package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.BoardRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MakeBoardMoveUseCaseTest {

    @Test
    fun `when invoke is called, then call repository move`() {
        val repository: BoardRepository = mockk(relaxUnitFun = true)
        val useCase = MakeBoardMoveUseCase(repository)

        runBlocking { useCase(0, 0) }

        coVerify { repository.move(0, 0) }
    }
}