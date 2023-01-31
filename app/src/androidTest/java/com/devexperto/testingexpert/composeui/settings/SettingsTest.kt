package com.devexperto.testingexpert.composeui.settings

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.composeui.ComposeActivity
import com.devexperto.testingexpert.composeui.assertMatchesGolden
import com.devexperto.testingexpert.composeui.onNodeWithContentDescription
import com.devexperto.testingexpert.composeui.onNodeWithText
import com.devexperto.testingexpert.data.SettingsRepository
import com.devexperto.testingexpert.ui.InstrumentedTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SettingsTest : InstrumentedTest() {

    @get:Rule(order = 2)
    val activityRule = createAndroidComposeRule<ComposeActivity>()

    @get:Rule(order = 3)
    var nameRule = TestName()

    @Inject
    lateinit var settingsRepository: SettingsRepository

    @Test
    fun onStart_theresAnOverflowInToolbar(): Unit = with(activityRule) {

        onNodeWithContentDescription(R.string.more_options).assertExists()
    }

    @Test
    fun onOverflowClick_theresASettingsItem(): Unit = with(activityRule) {

        onNodeWithContentDescription(R.string.more_options).performClick()

        onNodeWithText(R.string.settings).assertExists()
    }

    @Test
    fun onSettingsClick_showsDialog(): Unit = with(activityRule) {

        onNode(isDialog()).assertDoesNotExist()
        onNodeWithContentDescription(R.string.more_options).performClick()

        onNodeWithText(R.string.settings).performClick()

        onNode(isDialog()).assertIsDisplayed()
    }

    @Test
    fun onSettingsClick_closesOverflow(): Unit = with(activityRule) {

        onNodeWithContentDescription(R.string.more_options).performClick()
        onNodeWithText(R.string.settings).performClick()

        onNodeWithText(R.string.settings).assertDoesNotExist()
    }


    @Test
    fun onSettingsOpen_ButtonClosesDialog(): Unit = with(activityRule) {

        onNode(isDialog()).assertDoesNotExist()

        onNodeWithContentDescription(R.string.more_options).performClick()
        onNodeWithText(R.string.settings).performClick()
        onNode(isDialog()).assertIsDisplayed()

        onNodeWithText(R.string.close).performClick()
        onNode(isDialog()).assertDoesNotExist()
    }

    @Test
    fun onSettingsOpen_SavedThemeIsSelectedFirst(): Unit = with(activityRule) {

        settingsRepository.saveTheme(Theme.DARK)

        onNodeWithContentDescription(R.string.more_options).performClick()
        onNodeWithText(R.string.settings).performClick()

        onNodeWithContentDescription(R.string.dark).assertIsSelected()
    }

    @Test
    fun onThemeChanged_ThemeIsApplied(): Unit = with(activityRule) {

        onNodeWithContentDescription(R.string.more_options).performClick()
        onNodeWithText(R.string.settings).performClick()

        onNodeWithContentDescription(R.string.dark).performClick()
        onNodeWithText(R.string.close).performClick()

        mainClock.advanceTimeBy(2000)

        onRoot().assertMatchesGolden("screenshots/SettingsScreenshotTest_saveStartScreenToBitmap_dark.png")
    }

}