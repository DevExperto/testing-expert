package com.devexperto.testingexpert.data.datasource

import com.devexperto.testingexpert.domain.Score
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ScoreLocalDataSourceFake(scores: List<Score> = emptyList()) : ScoreLocalDataSource {

    private val inMemoryScores = MutableStateFlow(scores)

    override val scores: Flow<List<Score>> = inMemoryScores

    override suspend fun addScore(score: Score) {
        inMemoryScores.update { it + score }
    }

}