package com.devexperto.testingexpert.ui.games

import app.cash.turbine.test
import com.devexperto.testingexpert.ui.InstrumentedIntegrationTest
import com.devexperto.testingexpert.usecases.GetPopularGamesUseCase
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class GamesViewModelIntTest : InstrumentedIntegrationTest() {

    @Inject
    lateinit var getPopularGamesUseCase: GetPopularGamesUseCase

    private lateinit var viewModel: GamesViewModel

    @Before
    fun setUp() {
        viewModel = GamesViewModel(getPopularGamesUseCase)
    }

    @Test
    fun onCreated_callGetGames() = runTest {

        viewModel.state.test {
            assertEquals(GamesViewModel.UiState(), awaitItem())

            viewModel.onUiReady()
            assertEquals(GamesViewModel.UiState(isLoading = true), awaitItem())
            assertEquals("Grand Theft Auto V", awaitItem().games[0].name)
        }

    }

}