package com.devexperto.testingexpert.ui.games

import app.cash.turbine.test
import com.devexperto.testingexpert.data.GamesRepository
import com.devexperto.testingexpert.data.datasource.GamesRemoteDataSourceFake
import com.devexperto.testingexpert.domain.VideoGame
import com.devexperto.testingexpert.testrules.CoroutinesTestRule
import com.devexperto.testingexpert.usecases.GetPopularGamesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class GamesViewModelIntTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `when view model is created, then call get games`() = runTest {
        val expectedGames = listOf(VideoGame(1, "name", 1.0, "image", Date()))
        val repository = GamesRepository(GamesRemoteDataSourceFake(expectedGames))
        val getPopularGamesUseCase = GetPopularGamesUseCase(repository)
        val viewModel = GamesViewModel(getPopularGamesUseCase)

        viewModel.state.test {
            assertEquals(GamesViewModel.UiState(), awaitItem())

            viewModel.onUiReady()
            assertEquals(GamesViewModel.UiState(isLoading = true), awaitItem())
            assertEquals(GamesViewModel.UiState(games = expectedGames), awaitItem())
        }

    }

}