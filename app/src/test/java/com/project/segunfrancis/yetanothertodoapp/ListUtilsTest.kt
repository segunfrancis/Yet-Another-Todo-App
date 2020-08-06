package com.project.segunfrancis.yetanothertodoapp

import com.project.segunfrancis.yetanothertodoapp.ui.list.determineCardColor
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Created by SegunFrancis
 */

@RunWith(Parameterized::class)
class ListUtilsTest(
    private val expected: Int,
    private val dueDate: Long?,
    private val done: Boolean,
    private val scenario: String
) {

    companion object {
        private val now = System.currentTimeMillis()
        private const val day = 1000 * 60 * 60 * 24

        @JvmStatic
        @Parameterized.Parameters(name = "determineCardColor: {3}")
        fun toDos() = listOf(
            arrayOf(R.color.todoDone, null, true, "Done, no date"),
            arrayOf(R.color.todoNotDue, null, false, "Not done, no date"),
            arrayOf(R.color.todoOverDue, now - day, false, "Not done, due yesterday"),
            arrayOf(R.color.todoDueStrong, now + (day * 7), false, "Not done, due in 7 days"),
            arrayOf(R.color.todoDueMedium, now + (day * 14), false, "Not done, due in 14 days"),
            arrayOf(R.color.todoDueLight, now + (day * 20), false, "Not done, due in 16 days and above")
        )
    }

    @Test
    fun test_determineCardColor() {

        val actual = determineCardColor(dueDate, done)
        assertEquals(expected, actual)
    }
}