package com.buggyarts.hubtrends.test

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.buggyarts.hubtrends.R
import com.buggyarts.hubtrends.ui.main.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Rule
    private val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(
        MainActivity::class.java, false, false
    )

    private lateinit var mockWebServer: MockWebServer

    @Before
    @Throws(Exception::class)
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start(TestUtils.LOCAL_PORT)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testHappyCondition() {
        mockWebServer.dispatcher = MockServerDispatcher()
        activityRule.launchActivity(Intent())
        onView(withId(R.id.iv_three_dots)).check(matches(isDisplayed()))
    }

    @Test
    fun sampleTest() {
        activityRule.launchActivity(Intent())
        onView(withText("Trending"))
            .check(matches(isDisplayed()))
    }
}