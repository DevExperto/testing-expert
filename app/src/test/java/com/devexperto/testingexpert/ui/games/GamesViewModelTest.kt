package com.devexperto.testingexpert.ui.games

import app.cash.turbine.test
import com.devexperto.testingexpert.domain.VideoGame
import com.devexperto.testingexpert.testrules.CoroutinesExtension
import com.devexperto.testingexpert.usecases.GetPopularGamesUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutinesExtension::class)
class GamesViewModelTest {

    @Test
    fun `when view model is created, then call get games`() = runTest {
        val expectedGames = listOf(VideoGame(1, "name", 1.0, "image", Date()))
        val getPopularGamesUseCase: GetPopularGamesUseCase = mockk()
        every { getPopularGamesUseCase() } returns flowOf(expectedGames)
        val viewModel = GamesViewModel(getPopularGamesUseCase)

        viewModel.state.test {
            assertEquals(GamesViewModel.UiState(), awaitItem())

            viewModel.onUiReady()
            assertEquals(GamesViewModel.UiState(isLoading = true), awaitItem())
            assertEquals(GamesViewModel.UiState(games = expectedGames), awaitItem())
        }

    }

}