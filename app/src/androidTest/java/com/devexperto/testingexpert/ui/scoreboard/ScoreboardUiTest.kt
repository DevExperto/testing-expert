package com.devexperto.testingexpert.ui.scoreboard

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.data.local.ScoreDao
import com.devexperto.testingexpert.data.local.ScoreEntity
import com.devexperto.testingexpert.domain.X
import com.devexperto.testingexpert.ui.InstrumentedTest
import com.devexperto.testingexpert.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ScoreboardUiTest : InstrumentedTest() {

    @get:Rule(order = 2)
    val activityRule = activityScenarioRule<MainActivity>()

    @Inject
    lateinit var scoreDao: ScoreDao

    @Test
    fun whenScoreboardIsShown_dateShouldBeProperlyFormatted() {
        scoreDao.preloadScoreboardWithDate(2022, 1, 1)

        onView(withId(R.id.navigation_scoreboard)).perform(click())

        onView(withId(R.id.date)).check(matches(withText("2022-02-01")))
    }
}

@Suppress("SameParameterValue")
fun ScoreDao.preloadScoreboardWithDate(year: Int, month: Int, day: Int) {
    val cal = Calendar.getInstance()
    cal.set(year, month, day)
    val date = cal.time
    runBlocking { save(ScoreEntity(0, X, 5, date)) }
}