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
}