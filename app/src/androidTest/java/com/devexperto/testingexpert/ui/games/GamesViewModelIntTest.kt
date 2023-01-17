package com.devexperto.testingexpert.ui.games

import androidx.test.espresso.IdlingRegistry
import app.cash.turbine.test
import com.devexperto.testingexpert.data.remote.MockWebServerRule
import com.devexperto.testingexpert.idlingresources.OkHttp3IdlingResource
import com.devexperto.testingexpert.testrules.CoroutinesTestRule
import com.devexperto.testingexpert.usecases.GetPopularGamesUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
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
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var getPopularGamesUseCase: GetPopularGamesUseCase

    private lateinit var viewModel: GamesViewModel

    @Before
    fun setUp() {
        hiltRule.inject()

        val resource = OkHttp3IdlingResource.create("OkHttp", okHttpClient)
        IdlingRegistry.getInstance().register(resource)

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