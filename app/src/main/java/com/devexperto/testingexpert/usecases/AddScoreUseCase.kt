package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.ScoreboardRepository
import com.devexperto.testingexpert.domain.Score
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.findWinner
import com.devexperto.testingexpert.domain.numberOfMoves
import java.util.*
import javax.inject.Inject

class AddScoreUseCase @Inject constructor(
    private val scoreboardRepository: ScoreboardRepository
) {
    suspend operator fun invoke(board: TicTacToe) {
        board.findWinner()?.let { winner ->
            scoreboardRepository.addScore(
                Score(
                    winner = winner,
                    numberOfMoves = board.numberOfMoves(),
                    date = Date()
                )
            )
        }
    }
}