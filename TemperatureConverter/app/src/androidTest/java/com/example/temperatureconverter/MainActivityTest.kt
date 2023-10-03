package com.example.temperatureconverter

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    private val idlingResource = CountingIdlingResource("background_operations")
    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @After
    fun tearDown() {
        scenario.close()
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun testInitialCelState() {
        onView(withId(R.id.Tv_C)).check(matches(withText("Celsius")))

    }

    @Test
    fun testInitialFahrState() {
        onView(withId(R.id.Tv_F)).check(matches(withText("Fahrenheit")))
    }



}