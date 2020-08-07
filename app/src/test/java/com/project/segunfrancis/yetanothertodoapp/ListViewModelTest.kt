package com.project.segunfrancis.yetanothertodoapp

import com.project.segunfrancis.yetanothertodoapp.data.ToDo
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRepository
import com.project.segunfrancis.yetanothertodoapp.ui.list.ToDoListViewModel
import com.project.segunfrancis.yetanothertodoapp.util.ToDoTestRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

/**
 * Created by SegunFrancis
 */

@ExperimentalCoroutinesApi
class ListViewModelTest {

    @get:Rule
    val exceptionRule = ExpectedException.none()
    private lateinit var repository: ToDoRepository

    @Before
    fun setUp() {
        val now = System.currentTimeMillis()
        val day = 1000 * 60 * 60 * 24

        val toDos = ArrayList<ToDo>()
        var toDo = ToDo("1", "Todo 1", null, false, now)
        toDos.add(toDo)
        toDo = ToDo("2", "Todo 2", now + day, false, now)
        toDos.add(toDo)
        toDo = ToDo("3", "Todo 3", now + day, false, now)
        toDos.add(toDo)
        toDo = ToDo("4", "Todo 4", now + day, true, now)
        toDos.add(toDo)
        toDo = ToDo("5", "Todo 5", now + day, false, now)
        toDos.add(toDo)

        repository = ToDoTestRepository(toDos)
    }

    @Test
    fun test_allToDos() {
        val expected = 5
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
    fun test_upcomingToDosCount() {
        val expected = 3
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
        val model = ToDoListViewModel(repository)
        exceptionRule.expect(NotImplementedError::class.java)
        model.toggleToDo(id)
    }
}