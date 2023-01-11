package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.GamesRepository
import com.devexperto.testingexpert.data.datasource.GamesRemoteDataSourceFake
import com.devexperto.testingexpert.domain.VideoGame
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class GetPopularGamesUseCaseIntTest {

    @Test
    fun `when invoke is called, then return games from repository`() {
        val expectedGames = listOf(VideoGame(1, "Game 1", 1.0, "url", Date()))
        val dataSource = GamesRemoteDataSourceFake(expectedGames)
        val repository = GamesRepository(dataSource)
        val useCase = GetPopularGamesUseCase(repository)

        val games = runBlocking { useCase().first() }

        assertEquals(expectedGames, games)
    }
}