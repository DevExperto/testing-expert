package com.devexperto.testingexpert.composeui.board

import androidx.compose.foundation.layout.Row
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.devexperto.testingexpert.domain.Empty
import com.devexperto.testingexpert.domain.X
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLog

@RunWith(RobolectricTestRunner::class)
class CellUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var onClickCalled = false
    private val onClick: () -> Unit = { onClickCalled = true }

    @Before
    fun setUp() {
        ShadowLog.stream = System.out
        onClickCalled = false
    }

    @Test
    fun whenCellIsEmpty_ItCanBeClicked(): Unit = with(composeTestRule) {
        setContent {
            Row {
                Cell(Empty, onClick)
            }
        }

        onNodeWithText(Empty.toString()).performClick()

        assertTrue(onClickCalled)
    }

    @Test
    fun whenCellIsNotEmpty_ItCannotBeClicked(): Unit = with(composeTestRule) {
        setContent {
            Row {
                Cell(X, onClick)
            }
        }

        onNodeWithText(X.toString()).performClick()

        assertFalse(onClickCalled)
    }
}