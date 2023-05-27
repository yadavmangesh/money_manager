package com.mangesh.expensemanager

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.mangesh.expensemanager.home.ui.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test fun fab_click_test(){
        onView(withId(R.id.fab_add)).perform(click())
    }

    @Test fun dialog_trigger_test(){
        onView(withId(R.id.fab_add)).perform(click())
        onView(withId(R.id.btn_add)).check(matches(isDisplayed()))
    }
}