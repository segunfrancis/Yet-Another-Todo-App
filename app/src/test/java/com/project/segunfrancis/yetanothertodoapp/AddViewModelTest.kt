package com.project.segunfrancis.yetanothertodoapp

import com.nhaarman.mockitokotlin2.*
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRepository
import com.project.segunfrancis.yetanothertodoapp.ui.add.AddViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

/**
 * Created by SegunFrancis
 */

@ExperimentalCoroutinesApi
class AddViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun test_saveToDoWithoutDate() = coroutineRule.runBlockingTest {
        val repository: ToDoRepository = mock()
        val model = AddViewModel(repository, coroutineRule.testDispatcher)
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
    fun test_saveToDoWithDate() = coroutineRule.runBlockingTest {
        val repository: ToDoRepository = mock()
        val model = AddViewModel(repository, coroutineRule.testDispatcher)
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
    fun test_saveToDoNoTitle() = coroutineRule.runBlockingTest {
        val repository: ToDoRepository = mock()
        val model = AddViewModel(repository, coroutineRule.testDispatcher)
        val expected = "Title is required"

        val actual = model.save()

        assertNotNull(actual)
        assertEquals(expected, actual)
        verify(
            repository,
            never()
        ).insert(any()) // never() prevents the verify() method from being called when a toDo does'nt have a title
    }
}