package com.devexperto.testingexpert.composeui.board

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.composeui.ctx
import com.devexperto.testingexpert.composeui.onNodeWithText
import com.devexperto.testingexpert.domain.GameState
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.X
import com.devexperto.testingexpert.domain.move
import com.devexperto.testingexpert.ui.board.BoardViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BoardUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @MockK
    lateinit var onStartClick: () -> Unit

    @MockK
    lateinit var onMove: (Int, Int) -> Unit

    @MockK
    lateinit var onPlayAgainClick: () -> Unit

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxed = true)

    @Test
    fun givenANotStartedBoard_showsStartGame(): Unit = with(composeTestRule) {
        buildBoard(state = BoardViewModel.UiState(gameState = GameState.NotStarted))

        onNodeWithText(id = R.string.start_game, ignoreCase = true).assertExists()
    }

    @Test
    fun givenANotStartedBoard_clickOnStartIsCalled(): Unit = with(composeTestRule) {
        buildBoard(state = BoardViewModel.UiState(gameState = GameState.NotStarted))

        onNodeWithText(id = R.string.start_game, ignoreCase = true).performClick()

        verify { onStartClick.invoke() }
    }

    @Test
    fun givenAnInProgressBoard_clickOnEmptyCellCallsMove(): Unit = with(composeTestRule) {
        buildBoard(state = BoardViewModel.UiState(gameState = GameState.InProgress))

        onAllNodes(hasClickAction())[5].performClick()

        verify { onMove.invoke(1, 2) }
    }

    @Test
    fun givenAnInProgressBoard_clickOnOccupiedCellWontCallMove(): Unit = with(composeTestRule) {
        buildBoard(
            state = BoardViewModel.UiState(
                ticTacToe = TicTacToe().move(1, 2),
                gameState = GameState.InProgress
            )
        )

        onAllNodes(hasClickAction())[5].performClick()

        verify(exactly = 0) { onMove.invoke(1, 2) }
    }

    @Test
    fun givenAFinishedBoard_showsPlayAgain(): Unit = with(composeTestRule) {
        buildBoard(state = BoardViewModel.UiState(gameState = GameState.Finished(X)))

        onNodeWithText(id = R.string.play_again, ignoreCase = true).assertExists()
    }

    @Test
    fun givenAFinishedBoard_clickOnPlayAgainIsCalled(): Unit = with(composeTestRule) {
        buildBoard(state = BoardViewModel.UiState(gameState = GameState.Finished(X)))

        onNodeWithText(id = R.string.play_again, ignoreCase = true).performClick()

        verify { onPlayAgainClick.invoke() }
    }

    @Test
    fun givenAFinishedBoard_messageWithWinnerIsShown(): Unit = with(composeTestRule) {
        buildBoard(state = BoardViewModel.UiState(gameState = GameState.Finished(X)))

        onNodeWithText(
            ctx.getString(R.string.winner, X.toString()),
            ignoreCase = true
        ).assertExists()
    }


    private fun ComposeContentTestRule.buildBoard(state: BoardViewModel.UiState) {
        setContent {
            Board(
                state = state,
                onStartClick = onStartClick,
                onMove = onMove,
                onPlayAgainClick = onPlayAgainClick
            )
        }
    }
}