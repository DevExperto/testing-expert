package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.BoardRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyBlocking

class ResetBoardUseCaseTest {

    @Test
    fun `when invoke is called, then call repository reset`() {
        val repository: BoardRepository = mock()
        val useCase = ResetBoardUseCase(repository)

        runBlocking { useCase() }

        verifyBlocking(repository) { reset() }
    }
}