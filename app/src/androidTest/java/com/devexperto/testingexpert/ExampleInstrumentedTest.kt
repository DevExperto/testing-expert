package com.devexperto.testingexpert

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devexperto.testingexpert.data.datasource.GamesRemoteDataSource
import com.devexperto.testingexpert.data.remote.MockWebServerRule
import com.devexperto.testingexpert.data.remote.fromJson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
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

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @Inject
    lateinit var remoteDataSource: GamesRemoteDataSource

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testMockWebServerWorksWithEnqueue() = runTest {
        mockWebServerRule.server.enqueue(MockResponse().fromJson("mock_games.json"))

        val response = remoteDataSource.getGames()

        assertEquals("Grand Theft Auto V", response[0].name)
    }

    @Test
    fun testMockWebServerWorksWith() = runTest {
        mockWebServerRule.server.dispatcher = MockDispatcher()

        val response = remoteDataSource.getGames()
        assertEquals("Grand Theft Auto V", response[0].name)
    }

}

class MockDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when(request.requestUrl?.pathSegments?.get(0)) {
            "games" -> MockResponse().fromJson("mock_games.json")
            else -> MockResponse().setResponseCode(404)
        }
    }

}
