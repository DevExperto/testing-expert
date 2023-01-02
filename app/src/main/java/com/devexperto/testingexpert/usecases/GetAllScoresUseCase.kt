package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.ScoreboardRepository
import javax.inject.Inject

class GetAllScoresUseCase @Inject constructor(private val scoreboardRepository: ScoreboardRepository) {

    operator fun invoke() = scoreboardRepository.scores
}