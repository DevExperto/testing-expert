package com.devexperto.testingexpert.composeui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.*
import androidx.test.core.graphics.writeToTestStorage
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert

val ctx = InstrumentationRegistry.getInstrumentation().context
val targetCtx: Context = InstrumentationRegistry.getInstrumentation().targetContext

fun hasText(
    @StringRes id: Int,
    substring: Boolean = false,
    ignoreCase: Boolean = false
): SemanticsMatcher {
    val text = targetCtx.getString(id)
    return hasText(text, substring, ignoreCase)
}

fun SemanticsNodeInteractionsProvider.onNodeWithText(
    @StringRes id: Int,
    substring: Boolean = false,
    ignoreCase: Boolean = false,
    useUnmergedTree: Boolean = false
): SemanticsNodeInteraction {
    return onNode(hasText(id, substring, ignoreCase), useUnmergedTree)
}

fun SemanticsNodeInteractionsProvider.onNodeWithContentDescription(
    @StringRes id: Int,
    substring: Boolean = false,
    ignoreCase: Boolean = false,
    useUnmergedTree: Boolean = false
): SemanticsNodeInteraction {
    val text = targetCtx.getString(id)
    return onNodeWithContentDescription(text, substring, ignoreCase, useUnmergedTree)
}

fun SemanticsNodeInteraction.writeToTestStorage(assetFile: String) {
    val bitmap = captureToImage().asAndroidBitmap()
    bitmap.writeToTestStorage(assetFile)
}

fun SemanticsNodeInteraction.assertMatchesGolden(assetFile: String) {
    val bitmap = captureToImage().asAndroidBitmap()
    bitmap.assertMatchesGolden(assetFile)
}


fun Bitmap.assertMatchesGolden(assetFile: String) {
    val golden = ctx.assets
        .open(assetFile)
        .use(BitmapFactory::decodeStream)

    Assert.assertTrue(this.sameAs(golden))
}
