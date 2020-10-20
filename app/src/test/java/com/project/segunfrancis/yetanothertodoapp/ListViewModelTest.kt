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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner
import java.lang.IllegalArgumentException

/**
 * Created by SegunFrancis
 */

@ExperimentalCoroutinesApi
@RunWith(BlockJUnit4ClassRunner::class)
class ListViewModelTest {

    private val now = System.currentTimeMillis()
    private val day = 1000 * 60 * 60 * 24

    @get:Rule
    val exceptionRule = ExpectedException.none()

    @get:Rule
    val coroutineRule: MainCoroutineRule = MainCoroutineRule()

    @Test
    fun test_allToDosEmpty() = coroutineRule.runBlockingTest {
        val expected = 0
        val repository: ToDoRepository = mock()
        whenever(repository.getAllToDos())
            .thenReturn(flowOf(arrayListOf()))
        val model = ToDoListViewModel(repository, coroutineRule.testDispatcher)
        val toDos = model.allToDos

        assertNotNull(toDos)
        toDos.collect {
            assertEquals(expected, it.size)
        }
    }

    @Test
    fun test_allToDosSingle() = coroutineRule.runBlockingTest {
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
        val model = ToDoListViewModel(repository, coroutineRule.testDispatcher)
        val toDos = model.allToDos

        assertNotNull(toDos)
        toDos.collect {
            assertEquals(expected, it.size)
        }
    }

    @Test
    fun test_allToDosMultiple() = coroutineRule.runBlockingTest {
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
        val model = ToDoListViewModel(repository, coroutineRule.testDispatcher)
        val toDos = model.allToDos

        assertNotNull(toDos)
        toDos.collect {
            assertEquals(expected, it.size)
        }
    }

    @Test
    fun test_upcomingToDosCountEmpty() = coroutineRule.runBlockingTest {
        val expected = 0
        val repository: ToDoRepository = mock()
        whenever(repository.getUpcomingToDosCount())
            .thenReturn(flowOf(expected))
        val model = ToDoListViewModel(repository, coroutineRule.testDispatcher)
        val count = model.upcomingToDosCount

        assertNotNull(count)
        count.collect {
            assertEquals(expected, it)
        }
    }

    @Test
    fun test_upcomingToDosCountSingle() = coroutineRule.runBlockingTest {
        val expected = 1
        val repository: ToDoRepository = mock()
        whenever(repository.getUpcomingToDosCount())
            .thenReturn(flowOf(expected))
        val model = ToDoListViewModel(repository, coroutineRule.testDispatcher)
        val count = model.upcomingToDosCount

        assertNotNull(count)
        count.collect {
            assertEquals(expected, it)
        }
    }

    @Test
    fun test_upcomingToDosCountMultiple() = coroutineRule.runBlockingTest {
        val expected = 5
        val repository: ToDoRepository = mock()
        whenever(repository.getUpcomingToDosCount())
            .thenReturn(flowOf(expected))
        val model = ToDoListViewModel(repository, coroutineRule.testDispatcher)
        val count = model.upcomingToDosCount

        assertNotNull(count)
        count.collect {
            assertEquals(expected, it)
        }
    }

    @Test
    fun test_toggleToDo() = coroutineRule.runBlockingTest {
        val id = "fake"
        val repository: ToDoRepository = mock()
        val model = ToDoListViewModel(repository, coroutineRule.testDispatcher)

        model.toggleToDo(id)

        verify(repository)
            .toggleTodo(id)
    }

    @Test
    fun test_toggleToDoNotFound() = coroutineRule.runBlockingTest {
        val repository: ToDoRepository = mock()
        val id = "fake"
        val exceptionMessage = "Todo not found"
        whenever(repository.toggleTodo(id))
            .thenThrow(IllegalArgumentException(exceptionMessage))
        val model = ToDoListViewModel(repository, coroutineRule.testDispatcher)
        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage(exceptionMessage)
        model.toggleToDo(id)
        verify(repository)
            .toggleTodo(id)
    }

    @Test
    fun test_deleteToDo() = coroutineRule.runBlockingTest {
        val repository: ToDoRepository = mock()
        val model = AddViewModel(repository, coroutineRule.testDispatcher)
        val listModel = ToDoListViewModel(repository, coroutineRule.testDispatcher)
        val actualTitle = "Test ToDo"
        val actualDate = System.currentTimeMillis()
        val toDo = ToDo("1", actualTitle, now + day, true, actualDate)
        model.toDo.title = toDo.title
        model.toDo.dueDate = toDo.dueDate
        model.save()

        listModel.deleteToDo(toDo)
        verify(repository).delete(toDo)
    }
}