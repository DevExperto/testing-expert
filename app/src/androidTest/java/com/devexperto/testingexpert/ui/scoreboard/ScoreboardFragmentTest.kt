package com.devexperto.testingexpert.ui.scoreboard

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.data.local.ScoreDao
import com.devexperto.testingexpert.launchFragmentInHiltContainer
import com.devexperto.testingexpert.ui.InstrumentedTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ScoreboardFragmentTest : InstrumentedTest() {

    @Inject
    lateinit var scoreDao: ScoreDao

    @Test
    fun testFragmentLoad() {
        scoreDao.preloadScoreboardWithDate(2022, 1, 1)

        launchFragmentInHiltContainer<ScoreboardFragment>()

        onView(withId(R.id.date)).check(matches(ViewMatchers.withText("2022-02-01")))
    }
}