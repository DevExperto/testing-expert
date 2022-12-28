package com.devexperto.testingexpert.data

import com.devexperto.testingexpert.data.datasource.ScoreDataSource
import com.devexperto.testingexpert.domain.Score
import javax.inject.Inject

class ScoreboardRepository @Inject constructor(private val scoreDataSource: ScoreDataSource) {

    val scores = scoreDataSource.scores

    suspend fun addScore(score: Score) {
        scoreDataSource.addScore(score)
    }

}