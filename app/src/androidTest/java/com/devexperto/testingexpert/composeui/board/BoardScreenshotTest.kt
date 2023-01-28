package com.devexperto.testingexpert.composeui.board

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.core.graphics.writeToTestStorage
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.devexperto.testingexpert.composeui.ComposeActivity
import com.devexperto.testingexpert.ui.InstrumentedTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.junit.runner.RunWith
import java.io.IOException

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class BoardScreenshotTest : InstrumentedTest() {

    @get:Rule(order = 2)
    val activityRule = createAndroidComposeRule<ComposeActivity>()

    @get:Rule(order = 3)
    var nameRule = TestName()

    @Test
    @Throws(IOException::class)
    fun saveStartScreenToBitmap(): Unit = with(activityRule) {
        onRoot()
            .captureToImage()
            .asAndroidBitmap()
            .writeToTestStorage("${javaClass.simpleName}_${nameRule.methodName}")
    }

    @Test
    fun compareStartScreen(): Unit = with(activityRule) {

        val current = onRoot()
            .captureToImage()
            .asAndroidBitmap()

        val ctx = InstrumentationRegistry.getInstrumentation().context
        val golden = ctx.assets
            .open("screenshots/AndroidComposeTestRule_screenshotTest.png")
            .use(BitmapFactory::decodeStream)

        assertTrue(current.sameAs(golden))
    }
}