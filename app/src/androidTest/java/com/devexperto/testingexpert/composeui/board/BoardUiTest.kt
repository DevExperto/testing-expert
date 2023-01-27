package com.devexperto.testingexpert.composeui.board

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.composeui.ComposeActivity
import com.devexperto.testingexpert.composeui.onNodeWithText
import com.devexperto.testingexpert.ui.InstrumentedTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class BoardUiTest : InstrumentedTest() {

    @get:Rule(order = 2)
    val activityRule = createAndroidComposeRule<ComposeActivity>()

    @Test
    fun whenStartClicked_gameBoardAppears(): Unit = with(activityRule) {

        onNodeWithText(R.string.start_game, ignoreCase = true).performClick()

        onNodeWithTag(BOARD_TEST_TAG).assertIsDisplayed()
    }

    @Test
    fun whenFirstCellClicked_cellIsMarkedWithX(): Unit = with(activityRule) {
        onNodeWithText(R.string.start_game, ignoreCase = true).performClick()

        onNodeWithTag(CELL_TEST_TAG + "01").performClick()

        onNodeWithTag(CELL_TEST_TAG + "01").assertTextEquals("X")
    }

    @Test
    fun whenSecondCellClicked_cellIsMarkedWithO(): Unit = with(activityRule) {
        onNodeWithText(R.string.start_game, ignoreCase = true).performClick()

        onNodeWithTag(CELL_TEST_TAG + "01").performClick()
        onNodeWithTag(CELL_TEST_TAG + "02").performClick()

        onNodeWithTag(CELL_TEST_TAG + "02").assertTextEquals("O")
    }

    @Test
    fun whenBackToBoard_previousGameIsVisible(): Unit = with(activityRule) {
        onNodeWithText(R.string.start_game, ignoreCase = true).performClick()
        onNodeWithTag(CELL_TEST_TAG + "01").performClick()
        onNodeWithTag(CELL_TEST_TAG + "02").performClick()
        onNodeWithText(R.string.scoreboard).performClick()

        onNodeWithText(R.string.game).performClick()

        onNodeWithTag(BOARD_TEST_TAG).assertIsDisplayed()
        onNodeWithTag(CELL_TEST_TAG + "01").assertTextEquals("X")
        onNodeWithTag(CELL_TEST_TAG + "02").assertTextEquals("O")
    }

    @Test
    fun whenGameEndsInDraw_aMessageShowsTheResultProperly(): Unit = with(activityRule) {
        onNodeWithText(R.string.start_game, ignoreCase = true).performClick()

        playFullGameInDraw()

        onNodeWithText(R.string.draw).assertIsDisplayed()
    }

    @Test
    fun whenGameEndsAndMovesToScoreBoardBackAndForth_scoreShouldntBeDuplicated(): Unit =
        with(activityRule) {
            onNodeWithText(R.string.start_game, ignoreCase = true).performClick()

            playFullGameWinsX()

            onNodeWithText(R.string.scoreboard).performClick()

            // Check that the list has only 1 item
            onAllNodes(hasParent(hasScrollToIndexAction())).assertCountEquals(1)

            onNodeWithText(R.string.game).performClick()
            onNodeWithText(R.string.scoreboard).performClick()

            // Check that the list still has 1 item
            onAllNodes(hasParent(hasScrollToIndexAction())).assertCountEquals(1)

        }

    private fun SemanticsNodeInteractionsProvider.playFullGameWinsX() {
        onNodeWithTag(CELL_TEST_TAG + "00").performClick()
        onNodeWithTag(CELL_TEST_TAG + "01").performClick()
        onNodeWithTag(CELL_TEST_TAG + "11").performClick()
        onNodeWithTag(CELL_TEST_TAG + "12").performClick()
        onNodeWithTag(CELL_TEST_TAG + "22").performClick()
    }

    private fun SemanticsNodeInteractionsProvider.playFullGameInDraw() {
        onNodeWithTag(CELL_TEST_TAG + "00").performClick()
        onNodeWithTag(CELL_TEST_TAG + "01").performClick()
        onNodeWithTag(CELL_TEST_TAG + "02").performClick()
        onNodeWithTag(CELL_TEST_TAG + "12").performClick()
        onNodeWithTag(CELL_TEST_TAG + "10").performClick()
        onNodeWithTag(CELL_TEST_TAG + "20").performClick()
        onNodeWithTag(CELL_TEST_TAG + "11").performClick()
        onNodeWithTag(CELL_TEST_TAG + "22").performClick()
        onNodeWithTag(CELL_TEST_TAG + "21").performClick()
    }
}