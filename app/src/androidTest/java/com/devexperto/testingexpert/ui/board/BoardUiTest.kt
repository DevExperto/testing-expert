package com.devexperto.testingexpert.ui.board

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.ui.InstrumentedTest
import com.devexperto.testingexpert.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class BoardUiTest : InstrumentedTest() {

    @get:Rule(order = 2)
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun whenStartClicked_gameBoardAppears() {

        onView(withId(R.id.start_btn)).perform(click())

        onView(withId(R.id.board_view)).check(matches(isDisplayed()))
    }

    @Test
    fun whenFirstCellClicked_cellIsMarkedWithX() {
        onView(withId(R.id.start_btn)).perform(click())

        onView(withId(R.id.btn_0_1)).perform(click())

        onView(withId(R.id.btn_0_1)).check(matches(withText("X")))
    }

    @Test
    fun whenSecondCellClicked_cellIsMarkedWithO() {
        onView(withId(R.id.start_btn)).perform(click())

        onView(withId(R.id.btn_0_1)).perform(click())
        onView(withId(R.id.btn_0_2)).perform(click())

        onView(withId(R.id.btn_0_2)).check(matches(withText("O")))
    }

    @Test
    fun whenBackToBoard_previousGameIsVisible() {
        onView(withId(R.id.start_btn)).perform(click())
        onView(withId(R.id.btn_0_1)).perform(click())
        onView(withId(R.id.btn_0_2)).perform(click())
        onView(withId(R.id.navigation_scoreboard)).perform(click())

        onView(withId(R.id.navigation_board)).perform(click())

        onView(withId(R.id.board_view)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_0_1)).check(matches(withText("X")))
        onView(withId(R.id.btn_0_2)).check(matches(withText("O")))
    }

    @Test
    fun whenGameEndsInDraw_aMessageShowsTheResultProperly() {
        onView(withId(R.id.start_btn)).perform(click())

        playFullGameInDraw()

        onView(withText(R.string.draw)).check(matches(isDisplayed()))
    }

    @Test
    fun whenGameEndsAndMovesToScoreBoardBackAndForth_scoreShouldntBeDuplicated() {
        onView(withId(R.id.start_btn)).perform(click())
        playFullGameWinsX()

        onView(withId(R.id.navigation_scoreboard)).perform(click())

        // Check that the list has only 1 item
        onView(withId(R.id.score)).check(matches(hasChildCount(1)))

        onView(withId(R.id.navigation_board)).perform(click())
        onView(withId(R.id.navigation_scoreboard)).perform(click())

        // Check that the list still has 1 item
        onView(withId(R.id.score)).check(matches(hasChildCount(1)))
    }

    private fun playFullGameWinsX() {
        onView(withId(R.id.btn_0_0)).perform(click())
        onView(withId(R.id.btn_0_1)).perform(click())
        onView(withId(R.id.btn_1_1)).perform(click())
        onView(withId(R.id.btn_1_2)).perform(click())
        onView(withId(R.id.btn_2_2)).perform(click())
    }

    private fun playFullGameInDraw() {
        onView(withId(R.id.btn_0_0)).perform(click())
        onView(withId(R.id.btn_0_1)).perform(click())
        onView(withId(R.id.btn_0_2)).perform(click())
        onView(withId(R.id.btn_1_2)).perform(click())
        onView(withId(R.id.btn_1_0)).perform(click())
        onView(withId(R.id.btn_2_0)).perform(click())
        onView(withId(R.id.btn_1_1)).perform(click())
        onView(withId(R.id.btn_2_2)).perform(click())
        onView(withId(R.id.btn_2_1)).perform(click())
    }
}