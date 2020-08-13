package com.project.segunfrancis.yetanothertodoapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.project.segunfrancis.yetanothertodoapp.ui.add.AddActivity
import org.junit.Rule
import org.junit.Test

/**
 * Created by SegunFrancis
 */
class AddActivityTest {

    @get:Rule
    val activityTestRule =  ActivityTestRule<AddActivity>(AddActivity::class.java)

    @Test
    fun test_isEditTextVisible() {
        onView(withId(R.id.tilTitle)).check(matches(isDisplayed()))
    }
}