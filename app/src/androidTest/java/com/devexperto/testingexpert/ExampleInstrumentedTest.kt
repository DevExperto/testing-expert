package com.devexperto.testingexpert

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devexperto.testingexpert.data.local.BoardDao
import com.devexperto.testingexpert.data.local.MoveEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ExampleInstrumentedTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var boardDao: BoardDao

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test2ItemsAdded() = runTest {
        boardDao.saveMove(MoveEntity(0,0, 0))
        boardDao.saveMove(MoveEntity(0,1, 1))

        boardDao.getBoard().first().let {
            assertEquals(2, it.size)
        }
    }

    @Test
    fun test3ItemsAdded() = runTest {
        boardDao.saveMove(MoveEntity(0,0, 0))
        boardDao.saveMove(MoveEntity(0,1, 1))
        boardDao.saveMove(MoveEntity(0,2, 2))

        boardDao.getBoard().first().let {
            assertEquals(3, it.size)
        }
    }
}