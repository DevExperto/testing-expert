package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.ScoreboardRepository
import com.devexperto.testingexpert.domain.Score
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.X
import com.devexperto.testingexpert.domain.move
import io.mockk.coJustRun
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
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

        val slot = slot<Score>()

        val repository: ScoreboardRepository = mockk {
            coJustRun { addScore(capture(slot)) }
        }
        val useCase = AddScoreUseCase(repository)

        runBlocking { useCase.invoke(boardWithWinnerX) }

        slot.captured.apply {
            assertEquals(X, winner)
            assertEquals(5, numberOfMoves)
            assertEquals(
                Date().toInstant().truncatedTo(ChronoUnit.MINUTES),
                date.toInstant().truncatedTo(ChronoUnit.MINUTES)
            )
        }

    }
}