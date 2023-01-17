package com.devexperto.testingexpert.ui.board

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devexperto.testingexpert.data.remote.MockWebServerRule
import com.devexperto.testingexpert.domain.GameState
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.move
import com.devexperto.testingexpert.idlingresources.OkHttp3IdlingResource
import com.devexperto.testingexpert.testrules.CoroutinesTestRule
import com.devexperto.testingexpert.ui.board.BoardViewModel.UiState
import com.devexperto.testingexpert.usecases.AddScoreUseCase
import com.devexperto.testingexpert.usecases.GetCurrentBoardUseCase
import com.devexperto.testingexpert.usecases.MakeBoardMoveUseCase
import com.devexperto.testingexpert.usecases.ResetBoardUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class BoardViewModelIntTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val coroutinesTestRule = CoroutinesTestRule()

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var makeBoardMoveUseCase: MakeBoardMoveUseCase

    @Inject
    lateinit var getCurrentBoardUseCase: GetCurrentBoardUseCase

    @Inject
    lateinit var addScoreUseCase: AddScoreUseCase

    @Inject
    lateinit var resetBoardUseCase: ResetBoardUseCase

    private lateinit var viewModel: BoardViewModel

    @Before
    fun setUp() {
        hiltRule.inject()

        val resource = OkHttp3IdlingResource.create("OkHttp", okHttpClient)
        IdlingRegistry.getInstance().register(resource)

        viewModel = BoardViewModel(
            makeBoardMoveUseCase,
            getCurrentBoardUseCase,
            addScoreUseCase,
            resetBoardUseCase
        )
    }

    @Test
    fun atTheBeginning_gameStateIsNotStarted() {
        assertEquals(UiState(gameState = GameState.NotStarted), viewModel.state.value)
    }

    @Test
    fun whenStartGame_gameStateIsInProgress() = runTest(coroutinesTestRule.testDispatcher) {

        viewModel.startGame()
        runCurrent()

        assertEquals(UiState(gameState = GameState.InProgress), viewModel.state.value)
    }

    @Test
    fun whenMove_stateIsUpdated() = runTest {
        viewModel.startGame()

        viewModel.move(0, 1)
        runCurrent()

        assertEquals(
            UiState(TicTacToe().move(0, 1), GameState.InProgress),
            viewModel.state.value
        )
    }

    @Test
    fun whenReset_gameIsCleared() = runTest {
        viewModel.startGame()
        viewModel.move(0, 0)

        viewModel.resetGame()
        runCurrent()

        assertEquals(UiState(TicTacToe(), GameState.InProgress), viewModel.state.value)
    }

}