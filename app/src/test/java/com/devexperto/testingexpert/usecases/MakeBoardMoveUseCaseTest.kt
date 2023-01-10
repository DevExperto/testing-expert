package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.BoardRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyBlocking

class MakeBoardMoveUseCaseTest {

    @Test
    fun `when invoke is called, then call repository move`() {
        val repository: BoardRepository = mock()
        val useCase = MakeBoardMoveUseCase(repository)

        runBlocking { useCase(0, 0) }

        verifyBlocking(repository) { move(0, 0) }
    }
}