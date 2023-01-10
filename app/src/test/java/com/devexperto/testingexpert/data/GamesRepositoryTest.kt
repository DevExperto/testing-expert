package com.devexperto.testingexpert.data

import com.devexperto.testingexpert.data.datasource.GamesRemoteDataSource
import com.devexperto.testingexpert.domain.VideoGame
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.util.*

class GamesRepositoryTest {

    @Test
    fun `when getGames is called, then return list of games from remote data source`() {
        val expectedGames =
            listOf(VideoGame(1, "Game 1", 1.0, "https://image.com/1", Date()))

        val gamesRemoteDataSource: GamesRemoteDataSource = mock {
            onBlocking { getGames() } doReturn expectedGames
        }
        val gamesRepository = GamesRepository(gamesRemoteDataSource)

        val game = runBlocking { gamesRepository.games.first() }

        assertEquals(expectedGames, game)
    }
}