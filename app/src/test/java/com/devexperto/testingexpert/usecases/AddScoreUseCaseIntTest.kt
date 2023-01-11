package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.ScoreboardRepository
import com.devexperto.testingexpert.data.datasource.ScoreLocalDataSourceFake
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.X
import com.devexperto.testingexpert.domain.move
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.temporal.ChronoUnit
import java.util.*

class AddScoreUseCaseIntTest {

    private val boardWithWinnerX = TicTacToe()
        .move(0, 0)
        .move(1, 0)
        .move(0, 1)
        .move(1, 1)
        .move(0, 2)

    @Test
    fun `when addScore is called, then add score to repository`() {
        val repository = ScoreboardRepository(ScoreLocalDataSourceFake())

        val useCase = AddScoreUseCase(repository)

        val scores = runBlocking {
            useCase.invoke(boardWithWinnerX)
            repository.scores.first()
        }

        scores.first().apply {
            assertEquals(X, winner)
            assertEquals(5, numberOfMoves)
            assertEquals(
                Date().toInstant().truncatedTo(ChronoUnit.MINUTES),
                date.toInstant().truncatedTo(ChronoUnit.MINUTES)
            )
        }

    }
}