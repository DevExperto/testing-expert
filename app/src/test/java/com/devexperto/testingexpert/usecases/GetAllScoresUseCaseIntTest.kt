package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.ScoreboardRepository
import com.devexperto.testingexpert.data.datasource.ScoreLocalDataSourceFake
import com.devexperto.testingexpert.domain.Score
import com.devexperto.testingexpert.domain.X
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class GetAllScoresUseCaseIntTest {

    @Test
    fun `when invoke is called, then return scores from repository`() {
        val expectedScores = listOf(Score(X, 3, Date()))
        val dataSource = ScoreLocalDataSourceFake(expectedScores)
        val repository = ScoreboardRepository(dataSource)
        val useCase = GetAllScoresUseCase(repository)

        val scores = runBlocking { useCase().first() }

        assertEquals(expectedScores, scores)
    }
}