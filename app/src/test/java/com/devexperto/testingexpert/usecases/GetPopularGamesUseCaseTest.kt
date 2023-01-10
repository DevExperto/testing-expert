package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.GamesRepository
import com.devexperto.testingexpert.domain.VideoGame
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.util.*

class GetPopularGamesUseCaseTest {

    @Test
    fun `when invoke is called, then return games from repository`() {
        val expectedGames = listOf(VideoGame(1, "Game 1", 1.0, "url", Date()))
        val repository = mock<GamesRepository>() {
            onBlocking { games } doReturn flowOf(expectedGames)
        }
        val useCase = GetPopularGamesUseCase(repository)

        val games = runBlocking { useCase().first() }

        assertEquals(expectedGames, games)
    }
}