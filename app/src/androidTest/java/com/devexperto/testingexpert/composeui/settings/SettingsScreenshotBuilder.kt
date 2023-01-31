package com.devexperto.testingexpert.composeui.settings

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devexperto.testingexpert.composeui.ComposeActivity
import com.devexperto.testingexpert.composeui.writeToTestStorage
import com.devexperto.testingexpert.ui.InstrumentedTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.junit.runner.RunWith
import java.io.IOException

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SettingsScreenshotBuilder : InstrumentedTest() {

    @get:Rule(order = 2)
    val activityRule = createAndroidComposeRule<ComposeActivity>()

    @get:Rule(order = 3)
    var nameRule = TestName()

    @Test
    @Throws(IOException::class)
    fun saveStartScreenToBitmap(): Unit = with(activityRule) {
        onRoot().writeToTestStorage("${javaClass.simpleName}_${nameRule.methodName}")
    }

}