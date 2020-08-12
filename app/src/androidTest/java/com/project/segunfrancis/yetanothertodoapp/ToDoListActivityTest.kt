package com.project.segunfrancis.yetanothertodoapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.project.segunfrancis.yetanothertodoapp.ui.add.AddActivity
import com.project.segunfrancis.yetanothertodoapp.ui.list.ToDoListActivity
import org.junit.Rule
import org.junit.Test

/**
 * Created by SegunFrancis
 */

class ToDoListActivityTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule(ToDoListActivity::class.java)

    @Test
    fun test_checkFloatingActionButtonVisibility() {
        onView(withId(R.id.fab)).check(matches(isDisplayed()))
    }

    @Test
    fun test_checkToolbarVisibility() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navigateToNextActivity() {
        onView(withId(R.id.fab)).perform(click())
        intended(hasComponent(AddActivity::class.java.name))
    }
}