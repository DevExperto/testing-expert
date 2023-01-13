package com.devexperto.testingexpert.ui.games

import app.cash.turbine.test
import com.devexperto.testingexpert.data.remote.MockWebServerRule
import com.devexperto.testingexpert.testrules.CoroutinesTestRule
import com.devexperto.testingexpert.usecases.GetPopularGamesUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class GamesViewModelIntTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val coroutinesTestRule = CoroutinesTestRule()

    @Inject
    lateinit var getPopularGamesUseCase: GetPopularGamesUseCase

    private lateinit var viewModel: GamesViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
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