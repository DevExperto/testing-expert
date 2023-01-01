package com.devexperto.testingexpert.data.datasource

import com.devexperto.testingexpert.data.local.ScoreDao
import com.devexperto.testingexpert.data.local.ScoreEntity
import com.devexperto.testingexpert.domain.Score
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ScoreLocalDataSource {

    val scores: Flow<List<Score>>

    suspend fun addScore(score: Score)
}

class RoomScoreDataSource @Inject constructor(private val scoreDao: ScoreDao) : ScoreLocalDataSource {

    override val scores: Flow<List<Score>>
        get() = scoreDao.getAll().map { scores -> scores.map { it.toScore() } }

    override suspend fun addScore(score: Score) {
        scoreDao.save(score.toEntity())
    }
}

private fun Score.toEntity(): ScoreEntity {
    return ScoreEntity(0, winner, numberOfMoves, date)
}

private fun ScoreEntity.toScore(): Score = Score(
    winner,
    numberOfMoves,
    date
)
