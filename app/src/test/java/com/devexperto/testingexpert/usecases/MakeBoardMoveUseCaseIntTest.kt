package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.BoardRepository
import com.devexperto.testingexpert.data.datasource.BoardLocalDataSourceFake
import com.devexperto.testingexpert.domain.X
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MakeBoardMoveUseCaseIntTest {

    @Test
    fun `when invoke is called, then call repository move`() {
        val repository = BoardRepository(BoardLocalDataSourceFake())
        val useCase = MakeBoardMoveUseCase(repository)

        val ticTacToe = runBlocking {
            useCase(0, 0)
            repository.board.first()
        }

        assertEquals(X, ticTacToe.board[0][0])
    }
}