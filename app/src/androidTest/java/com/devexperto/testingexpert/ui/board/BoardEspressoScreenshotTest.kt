package com.devexperto.testingexpert.ui.board

import androidx.test.core.graphics.writeToTestStorage
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.screenshot.captureToBitmap
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devexperto.testingexpert.composeui.assertMatchesGolden
import com.devexperto.testingexpert.ui.InstrumentedTest
import com.devexperto.testingexpert.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.junit.runner.RunWith
import java.io.IOException

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class BoardEspressoScreenshotTest : InstrumentedTest() {

    @get:Rule(order = 2)
    val activityRule = activityScenarioRule<MainActivity>()

    @get:Rule(order = 3)
    var nameRule = TestName()

    @Test
    @Throws(IOException::class)
    fun saveStartScreenToBitmap() {
        onView(isRoot())
            .captureToBitmap()
            .writeToTestStorage("${javaClass.simpleName}_${nameRule.methodName}")
    }

    @Test
    fun compareStartScreen() {
        onView(isRoot())
            .captureToBitmap()
            .assertMatchesGolden("screenshots/BoardEspressoScreenshotTest_saveStartScreenToBitmap.png")
    }
}
