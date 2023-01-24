package com.devexperto.testingexpert.ui.board

import com.devexperto.testingexpert.data.BoardRepository
import com.devexperto.testingexpert.data.ScoreboardRepository
import com.devexperto.testingexpert.data.datasource.BoardLocalDataSourceFake
import com.devexperto.testingexpert.data.datasource.ScoreLocalDataSourceFake
import com.devexperto.testingexpert.domain.GameState
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.move
import com.devexperto.testingexpert.testrules.CoroutinesTestRule
import com.devexperto.testingexpert.ui.board.BoardViewModel.UiState
import com.devexperto.testingexpert.usecases.AddScoreUseCase
import com.devexperto.testingexpert.usecases.GetCurrentBoardUseCase
import com.devexperto.testingexpert.usecases.MakeBoardMoveUseCase
import com.devexperto.testingexpert.usecases.ResetBoardUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BoardViewModelIntTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: BoardViewModel

    @Before
    fun setUp() {

        val boardRepository = BoardRepository(BoardLocalDataSourceFake())
        val scoreboardRepository = ScoreboardRepository(ScoreLocalDataSourceFake())

        viewModel = BoardViewModel(
            MakeBoardMoveUseCase(boardRepository, AddScoreUseCase(scoreboardRepository)),
            GetCurrentBoardUseCase(boardRepository),
            ResetBoardUseCase(boardRepository)
        )
    }

    @Test
    fun `at the beginning, the game is not started`() {
        assertEquals(UiState(gameState = GameState.NotStarted), viewModel.state.value)
    }

    @Test
    fun `when start game is called, game state is in progress`() = runTest {

        viewModel.startGame()
        runCurrent()

        assertEquals(UiState(gameState = GameState.InProgress), viewModel.state.value)
    }

    @Test
    fun `move updates the state`() = runTest {
        viewModel.startGame()

        viewModel.move(0, 1)
        runCurrent()

        assertEquals(
            UiState(TicTacToe().move(0, 1), GameState.InProgress),
            viewModel.state.value
        )
    }

    @Test
    fun `when reset is called, then the game is cleared`() = runTest {
        viewModel.startGame()
        viewModel.move(0, 0)

        viewModel.resetGame()
        runCurrent()

        assertEquals(UiState(TicTacToe(), GameState.InProgress), viewModel.state.value)
    }

}