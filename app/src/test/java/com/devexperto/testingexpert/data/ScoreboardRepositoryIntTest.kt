package com.devexperto.testingexpert.data

import com.devexperto.testingexpert.data.datasource.ScoreLocalDataSourceFake
import com.devexperto.testingexpert.domain.Score
import com.devexperto.testingexpert.domain.X
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class ScoreboardRepositoryIntTest {

    private val expectedScore = Score(X, 3, Date())

    @Test
    fun `when scores are requested, they are retrieved from the local data source`() {
        val localDataSource = ScoreLocalDataSourceFake(listOf(expectedScore))
        val repository = ScoreboardRepository(localDataSource)

        val scores = runBlocking { repository.scores.first() }

        assertEquals(expectedScore, scores.first())
    }

    @Test
    fun `when a score is added, it is returned properly in the scores list`() {
        val repository = ScoreboardRepository(ScoreLocalDataSourceFake())

        val scores = runBlocking {
            repository.addScore(expectedScore)
            repository.scores.first()
        }

        assertEquals(expectedScore, scores.first())
    }
}