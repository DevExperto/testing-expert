package com.devexperto.testingexpert.data

import com.devexperto.testingexpert.data.datasource.ScoreLocalDataSource
import com.devexperto.testingexpert.domain.Score
import com.devexperto.testingexpert.domain.X
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class ScoreboardRepositoryTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var localDataSource: ScoreLocalDataSource

    private lateinit var repository: ScoreboardRepository

    private val expectedScores = listOf(Score(X, 3, Date()))

    @Before
    fun setUp() {
        every { localDataSource.scores } returns flowOf(expectedScores)
        coJustRun { localDataSource.addScore(any()) }
        repository = ScoreboardRepository(localDataSource)
    }


    @Test
    fun `when a score is added, it is added to the local data source`() {
        val score = Score(X, 3, Date())

        runBlocking { repository.addScore(score) }

        coVerify { localDataSource.addScore(score) }
    }

    @Test
    fun `when scores are requested, they are retrieved from the local data source`() {
        val score = runBlocking { repository.scores.first() }

        assertEquals(expectedScores, score)
    }
}