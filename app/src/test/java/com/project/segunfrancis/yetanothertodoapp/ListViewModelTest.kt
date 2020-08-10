package com.project.segunfrancis.yetanothertodoapp

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.project.segunfrancis.yetanothertodoapp.data.ToDo
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRepository
import com.project.segunfrancis.yetanothertodoapp.ui.add.AddViewModel
import com.project.segunfrancis.yetanothertodoapp.ui.list.ToDoListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

/**
 * Created by SegunFrancis
 */

@ExperimentalCoroutinesApi
class ListViewModelTest {

    private val now = System.currentTimeMillis()
    private val day = 1000 * 60 * 60 * 24

    @get:Rule
    val exceptionRule = ExpectedException.none()

    @Test
    fun test_allToDosEmpty() {
        val expected = 0
        val repository: ToDoRepository = mock()
        whenever(repository.getAllToDos())
            .thenReturn(flowOf(arrayListOf()))
        val model = ToDoListViewModel(repository)
        val toDos = model.allToDos

        assertNotNull(toDos)
        runBlockingTest {
            toDos.collect {
                assertEquals(expected, it.size)
            }
        }
    }

    @Test
    fun test_allToDosSingle() {
        val expected = 1
        val repository: ToDoRepository = mock()
        whenever(repository.getAllToDos())
            .thenReturn(
                flowOf(
                    arrayListOf(
                        ToDo("5", "Todo 5", now + day, false, now)
                    )
                )
            )
        val model = ToDoListViewModel(repository)
        val toDos = model.allToDos

        assertNotNull(toDos)
        runBlockingTest {
            toDos.collect {
                assertEquals(expected, it.size)
            }
        }
    }

    @Test
    fun test_allToDosMultiple() {
        val expected = 3
        val repository: ToDoRepository = mock()
        whenever(repository.getAllToDos())
            .thenReturn(
                flowOf(
                    arrayListOf(
                        ToDo("5", "Todo 5", now + day, false, now),
                        ToDo("3", "Todo 3", now + day, false, now),
                        ToDo("2", "Todo 2", now + day, false, now)
                    )
                )
            )
        val model = ToDoListViewModel(repository)
        val toDos = model.allToDos

        assertNotNull(toDos)
        runBlockingTest {
            toDos.collect {
                assertEquals(expected, it.size)
            }
        }
    }

    @Test
    fun test_upcomingToDosCountEmpty() {
        val expected = 0
        val repository: ToDoRepository = mock()
        whenever(repository.getUpcomingToDosCount())
            .thenReturn(flowOf(expected))
        val model = ToDoListViewModel(repository)
        val count = model.upcomingToDosCount

        assertNotNull(count)
        runBlockingTest {
            count.collect {
                assertEquals(expected, it)
            }
        }
    }

    @Test
    fun test_upcomingToDosCountSingle() {
        val expected = 1
        val repository: ToDoRepository = mock()
        whenever(repository.getUpcomingToDosCount())
            .thenReturn(flowOf(expected))
        val model = ToDoListViewModel(repository)
        val count = model.upcomingToDosCount

        assertNotNull(count)
        runBlockingTest {
            count.collect {
                assertEquals(expected, it)
            }
        }
    }

    @Test
    fun test_upcomingToDosCountMultiple() {
        val expected = 5
        val repository: ToDoRepository = mock()
        whenever(repository.getUpcomingToDosCount())
            .thenReturn(flowOf(expected))
        val model = ToDoListViewModel(repository)
        val count = model.upcomingToDosCount

        assertNotNull(count)
        runBlockingTest {
            count.collect {
                assertEquals(expected, it)
            }
        }
    }

    @Test
    fun test_toggleToDo() {
        val id = "fake"
        val repository: ToDoRepository = mock()
        val model = ToDoListViewModel(repository)

        model.toggleToDo(id)

        verify(repository)
            .toggleTodo(id)
    }

    @Test
    fun test_toggleToDoNotFound() {
        val repository: ToDoRepository = mock()
        val id = "fake"
        val exceptionMessage = "Todo not found"

        whenever(repository.toggleTodo(id))
            .thenThrow(IllegalArgumentException(exceptionMessage))
        val model = ToDoListViewModel(repository)
        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage(exceptionMessage)
        model.toggleToDo(id)

        verify(repository)
            .toggleTodo(id)
    }

    @Test
    fun test_deleteToDo() {
        val repository: ToDoRepository = mock()
        val listModel = ToDoListViewModel(repository)
        val model = AddViewModel(repository)
        val actualTitle = "Test ToDo"
        val actualDate = System.currentTimeMillis()
        val toDo = ToDo("1", actualTitle, now + day, true, actualDate)
        model.toDo.title = toDo.title
        model.toDo.dueDate = toDo.dueDate

        runBlockingTest {
            repository.insert(toDo)
            listModel.deleteToDo(toDo)
            verify(repository).delete(toDo)
        }
    }
}