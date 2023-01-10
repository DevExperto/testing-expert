package com.devexperto.testingexpert.ui.games

import com.devexperto.testingexpert.domain.VideoGame
import com.devexperto.testingexpert.testrules.CoroutinesTestRule
import com.devexperto.testingexpert.usecases.GetPopularGamesUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class GamesViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `when view model is created, then call get games`() = runTest {
        val expectedGames = listOf(VideoGame(1, "name", 1.0, "image", Date()))
        val getPopularGamesUseCase: GetPopularGamesUseCase = mockk()
        every { getPopularGamesUseCase() } returns flow {
            delay(3000)
            emit(expectedGames)
        }
        val viewModel = GamesViewModel(getPopularGamesUseCase)

        viewModel.onUiReady()

        advanceTimeBy(50)
        assertEquals(GamesViewModel.UiState(isLoading = true), viewModel.state.value)

        advanceTimeBy(3000)
        assertEquals(expectedGames, viewModel.state.value.games)
    }

}