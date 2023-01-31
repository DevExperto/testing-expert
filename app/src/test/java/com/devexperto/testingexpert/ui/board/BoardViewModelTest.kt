package com.devexperto.testingexpert.ui.board

import app.cash.turbine.test
import com.devexperto.testingexpert.domain.GameState
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.testrules.CoroutinesExtension
import com.devexperto.testingexpert.ui.board.BoardViewModel.UiState
import com.devexperto.testingexpert.usecases.AddScoreUseCase
import com.devexperto.testingexpert.usecases.GetCurrentBoardUseCase
import com.devexperto.testingexpert.usecases.MakeBoardMoveUseCase
import com.devexperto.testingexpert.usecases.ResetBoardUseCase
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutinesExtension::class, MockKExtension::class)
class BoardViewModelTest {

    @MockK
    lateinit var makeBoardMoveUseCase: MakeBoardMoveUseCase

    @MockK
    lateinit var getCurrentBoardUseCase: GetCurrentBoardUseCase

    @MockK
    lateinit var addScoreUseCase: AddScoreUseCase

    @MockK
    lateinit var resetBoardUseCase: ResetBoardUseCase

    lateinit var viewModel: BoardViewModel

    @BeforeEach
    fun setUp() {
        every { getCurrentBoardUseCase() } returns flowOf(TicTacToe())
        viewModel = BoardViewModel(
            makeBoardMoveUseCase,
            getCurrentBoardUseCase,
            addScoreUseCase,
            resetBoardUseCase
        )
    }

    @Test
    fun `at the beginning, the game is not started`() = runTest {
        viewModel.state.test {
            assertEquals(UiState(gameState = GameState.NotStarted), awaitItem())
        }
    }

    @Test
    fun `when start game is called, game state is in progress`() = runTest {
        viewModel.state.test {
            assertEquals(UiState(gameState = GameState.NotStarted), awaitItem())

            viewModel.startGame()
            assertEquals(UiState(gameState = GameState.InProgress), awaitItem())
        }
    }

    @Test
    fun `when reset is called, then the game is cleared`() = runTest {
        coJustRun { resetBoardUseCase() }

        viewModel.resetGame()

        runCurrent()

        coVerify { resetBoardUseCase() }
    }

    @Test
    fun `move is recorded by use case`() = runTest {

        val slot1 = slot<Int>()
        val slot2 = slot<Int>()
        coJustRun { makeBoardMoveUseCase(capture(slot1), capture(slot2)) }

        viewModel.move(0, 1)

        runCurrent()

        assertEquals(0, slot1.captured)
        assertEquals(1, slot2.captured)
    }

}