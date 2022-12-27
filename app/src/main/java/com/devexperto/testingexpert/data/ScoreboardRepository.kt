package com.devexperto.testingexpert.data

import com.devexperto.testingexpert.domain.O
import com.devexperto.testingexpert.domain.Score
import com.devexperto.testingexpert.domain.X
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.*
import javax.inject.Inject

class ScoreboardRepository @Inject constructor() {

    fun getScores(): Flow<List<Score>> = flowOf(
        listOf(
            Score(winner = X, numberOfMoves = 3, date = Date()),
            Score(winner = O, numberOfMoves = 5, date = Date()),
            Score(winner = X, numberOfMoves = 2, date = Date()),
            Score(winner = O, numberOfMoves = 4, date = Date()),
            Score(winner = X, numberOfMoves = 1, date = Date()),
            Score(winner = O, numberOfMoves = 6, date = Date()),
            Score(winner = X, numberOfMoves = 7, date = Date()),
            Score(winner = O, numberOfMoves = 8, date = Date()),
            Score(winner = X, numberOfMoves = 9, date = Date()),
            Score(winner = O, numberOfMoves = 10, date = Date()),
            Score(winner = X, numberOfMoves = 11, date = Date()),
            Score(winner = O, numberOfMoves = 12, date = Date()),
            Score(winner = X, numberOfMoves = 13, date = Date()),
            Score(winner = O, numberOfMoves = 14, date = Date()),
            Score(winner = X, numberOfMoves = 15, date = Date()),
            Score(winner = O, numberOfMoves = 16, date = Date()),
            Score(winner = X, numberOfMoves = 17, date = Date()),
            Score(winner = O, numberOfMoves = 18, date = Date()),
            Score(winner = X, numberOfMoves = 19, date = Date()),
            Score(winner = O, numberOfMoves = 20, date = Date()),
            Score(winner = X, numberOfMoves = 21, date = Date()),
            Score(winner = O, numberOfMoves = 22, date = Date()),
            Score(winner = X, numberOfMoves = 23, date = Date()),
            Score(winner = O, numberOfMoves = 24, date = Date()),
            Score(winner = X, numberOfMoves = 25, date = Date()),
            Score(winner = O, numberOfMoves = 26, date = Date()),
            Score(winner = X, numberOfMoves = 27, date = Date()),
            Score(winner = O, numberOfMoves = 28, date = Date()),
        )
    )
}