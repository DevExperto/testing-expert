package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.ScoreboardRepository
import com.devexperto.testingexpert.domain.Score
import com.devexperto.testingexpert.domain.X
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class GetAllScoresUseCaseTest {

    @Test
    fun `when invoke is called, then return scores from repository`() {
        val expectedScores = listOf(Score(X, 3, Date()))
        val repository: ScoreboardRepository = mockk() {
            every { scores } returns flowOf(expectedScores)
        }
        val useCase = GetAllScoresUseCase(repository)

        val scores = runBlocking { useCase().first() }

        assertEquals(expectedScores, scores)
    }
}