package com.devexperto.testingexpert.data

import com.devexperto.testingexpert.data.datasource.ScoreLocalDataSource
import com.devexperto.testingexpert.domain.Score
import com.devexperto.testingexpert.domain.X
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verifyBlocking
import org.mockito.kotlin.whenever
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ScoreboardRepositoryTest {

    @Mock
    lateinit var localDataSource: ScoreLocalDataSource

    private lateinit var repository: ScoreboardRepository

    private val expectedScores = listOf(Score(X, 3, Date()))

    @Before
    fun setUp() {
        whenever(localDataSource.scores).thenReturn(flowOf(expectedScores))
        repository = ScoreboardRepository(localDataSource)
    }


    @Test
    fun `when a score is added, it is added to the local data source`() {
        val score = Score(X, 3, Date())

        runBlocking { repository.addScore(score) }

        verifyBlocking(localDataSource) { addScore(score) }
    }

    @Test
    fun `when scores are requested, they are retrieved from the local data source`() {
        val score = runBlocking { repository.scores.first() }

        assertEquals(expectedScores, score)
    }
}