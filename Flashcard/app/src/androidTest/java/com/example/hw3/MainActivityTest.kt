package com.example.hw3

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.not

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun showsNum1TextOnLaunch(){
        onView(withId(R.id.StartButton)).perform(click())
        onView(withId(R.id.number1TextView)).check(matches(not(withText("number 1"))))
    }

    @Test
    fun showsNum2TextOnLaunch(){
        onView(withId(R.id.StartButton)).perform(click())
        onView(withId(R.id.number2TextView)).check(matches(not(withText("number 2"))))
    }
}