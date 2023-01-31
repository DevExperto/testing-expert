package com.devexperto.testingexpert.composeui.settings

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.composeui.onNodeWithContentDescription
import com.devexperto.testingexpert.composeui.onNodeWithText
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SettingsUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var theme: MutableState<Theme>

    @MockK
    lateinit var onDismissRequest: () -> Unit

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        composeTestRule.setContent {
            theme = remember { mutableStateOf(Theme.SAME_AS_SYSTEM) }
            SettingsDialog(
                theme = theme.value,
                onChangeTheme = { theme.value = it },
                onDismissRequest = onDismissRequest
            )
        }
    }

    @Test
    fun showsDialogWithChangeThemeSetting(): Unit = with(composeTestRule) {
        onNodeWithText(R.string.change_theme).assertIsDisplayed()
    }

    @Test
    fun showsDialogWithCloseButton(): Unit = with(composeTestRule) {
        onNodeWithText(R.string.close).assertIsDisplayed()
    }

    @Test
    fun onCloseButtonClicked_dismissesDialog(): Unit = with(composeTestRule) {
        onNodeWithText(R.string.close).performClick()
        verify { onDismissRequest.invoke() }
    }

    @Test
    fun onThemeRadioClicked_ThemeRadioIsSelected(): Unit = with(composeTestRule) {
        onNodeWithContentDescription(R.string.same_as_system).assertIsSelected()

        onNodeWithContentDescription(R.string.light).performClick()

        onNodeWithContentDescription(R.string.same_as_system).assertIsNotSelected()
        onNodeWithContentDescription(R.string.light).assertIsSelected()
    }

    @Test
    fun onThemeRadioClicked_ChangeThemeIsCalled(): Unit = with(composeTestRule) {
        onNodeWithContentDescription(R.string.light).performClick()
        assertEquals(Theme.LIGHT, theme.value)
    }
}