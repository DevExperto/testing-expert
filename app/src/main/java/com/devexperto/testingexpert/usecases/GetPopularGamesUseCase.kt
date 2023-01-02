package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.GamesRepository
import javax.inject.Inject

class GetPopularGamesUseCase @Inject constructor(private val gamesRepository: GamesRepository) {
    operator fun invoke() = gamesRepository.games
}