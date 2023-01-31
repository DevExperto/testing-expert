package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.GamesRepository
import com.devexperto.testingexpert.domain.VideoGame
import kotlinx.coroutines.flow.first
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class GetPopularGamesUseCaseTest {

    @Test
    fun `when invoke is called, then return games from repository`() {
        val expectedGames = listOf(VideoGame(1, "Game 1", 1.0, "url", Date()))
        val repository: GamesRepository = mockk() {
            every { games } returns flowOf(expectedGames)
        }
        val useCase = GetPopularGamesUseCase(repository)

        val games = runBlocking { useCase().first() }

        assertEquals(expectedGames, games)
    }
}