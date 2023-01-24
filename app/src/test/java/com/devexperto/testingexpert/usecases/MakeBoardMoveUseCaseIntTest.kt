package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.BoardRepository
import com.devexperto.testingexpert.data.ScoreboardRepository
import com.devexperto.testingexpert.data.datasource.BoardLocalDataSourceFake
import com.devexperto.testingexpert.data.datasource.ScoreLocalDataSourceFake
import com.devexperto.testingexpert.domain.X
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MakeBoardMoveUseCaseIntTest {

    @Test
    fun `when invoke is called, then call repository move`() {
        val boardRepository = BoardRepository(BoardLocalDataSourceFake())
        val scoreboardRepository = ScoreboardRepository(ScoreLocalDataSourceFake())
        val addScoreUseCase = AddScoreUseCase(scoreboardRepository)
        val useCase = MakeBoardMoveUseCase(boardRepository, addScoreUseCase)

        val ticTacToe = runBlocking {
            useCase(0, 0)
            boardRepository.board.first()
        }

        assertEquals(X, ticTacToe.board[0][0])
    }
}