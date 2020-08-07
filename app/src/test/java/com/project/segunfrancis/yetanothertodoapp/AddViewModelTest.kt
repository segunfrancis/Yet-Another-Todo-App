package com.project.segunfrancis.yetanothertodoapp

import com.nhaarman.mockitokotlin2.*
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRepository
import com.project.segunfrancis.yetanothertodoapp.ui.add.AddViewModel
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by SegunFrancis
 */

class AddViewModelTest {

    @Test
    fun test_saveToDoWithoutDate() {
        val repository: ToDoRepository = mock()
        val model = AddViewModel(repository)
        val actualTitle = "Test ToDo"
        model.toDo.title = actualTitle

        val actual = model.save()

        assertNull(actual)
        verify(repository).insert(
            argThat {
                title == actualTitle && dueDate == null
            }
        )
    }

    @Test
    fun test_saveToDoWithDate() {
        val repository: ToDoRepository = mock()
        val model = AddViewModel(repository)
        val actualTitle = "Test ToDo"
        val actualDate = System.currentTimeMillis()
        model.toDo.title = actualTitle
        model.toDo.dueDate = actualDate

        val actual = model.save()

        assertNull(actual)
        verify(repository).insert(
            argThat {
                title == actualTitle && dueDate == actualDate
            }
        )
    }

    @Test
    fun test_saveToDoNoTitle() {
        val repository: ToDoRepository = mock()
        val model = AddViewModel(repository)
        val expected = "Title is required"

        val actual = model.save()

        assertNotNull(actual)
        assertEquals(expected, actual)
        verify(
            repository,
            never()
        ).insert(any()) // never() prevents the verify() method to be called when a toDo does'nt have a title
    }
}