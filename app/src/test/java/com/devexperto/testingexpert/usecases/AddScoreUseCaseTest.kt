package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.ScoreboardRepository
import com.devexperto.testingexpert.domain.Score
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.X
import com.devexperto.testingexpert.domain.move
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyBlocking
import java.time.temporal.ChronoUnit
import java.util.*

class AddScoreUseCaseTest {

    @Test
    fun `when addScore is called, then add score to repository`() {
        val boardWithWinnerX = TicTacToe()
            .move(0, 0)
            .move(1, 0)
            .move(0, 1)
            .move(1, 1)
            .move(0, 2)

        val repository: ScoreboardRepository = mock()
        val useCase = AddScoreUseCase(repository)

        runBlocking { useCase.invoke(boardWithWinnerX) }

        argumentCaptor<Score>().apply {
            verifyBlocking(repository) { addScore(capture()) }
            assertEquals(X, firstValue.winner)
            assertEquals(5, firstValue.numberOfMoves)
            assertEquals(
                Date().toInstant().truncatedTo(ChronoUnit.MINUTES),
                firstValue.date.toInstant().truncatedTo(ChronoUnit.MINUTES)
            )
        }

    }
}