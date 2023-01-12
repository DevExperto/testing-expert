package com.devexperto.testingexpert

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devexperto.testingexpert.data.GamesRepository
import com.devexperto.testingexpert.data.datasource.GamesRemoteDataSourceFake
import com.devexperto.testingexpert.domain.VideoGame
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
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
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ExampleInstrumentedTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var gamesRepository: GamesRepository

    @Inject
    lateinit var dataSource: GamesRemoteDataSourceFake

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testHiltWorks() {
        val expectedGames = listOf(
            VideoGame(
                id = 1,
                name = "Super Mario Bros",
                rating = 0.1,
                imageUrl = "https://www.google.com",
                releaseDate = Date()
            )
        )

        dataSource.games = expectedGames

        val games = runBlocking { gamesRepository.games.first() }

        assertEquals(expectedGames, games)
    }
}