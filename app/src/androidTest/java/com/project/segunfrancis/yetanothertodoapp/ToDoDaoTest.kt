package com.project.segunfrancis.yetanothertodoapp

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.project.segunfrancis.yetanothertodoapp.data.ToDo
import com.project.segunfrancis.yetanothertodoapp.data.ToDoDao
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRoomDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by SegunFrancis
 */

@RunWith(AndroidJUnit4::class)
class ToDoDaoTest {
    private lateinit var db: ToDoRoomDatabase
    private lateinit var dao: ToDoDao
    private val now = System.currentTimeMillis()
    private val day = 1000 * 60 * 60 * 24

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(context, ToDoRoomDatabase::class.java)
            .allowMainThreadQueries().build()
        dao = db.todoDao()
    }

    @Test
    fun test_insertToDoWithDueDate() {
        val expected = 1
        GlobalScope.launch {
            dao.insert(ToDo("1", "First Todo", now + day, false, now))
            dao.getDateCount(now).collect { actual ->
                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun test_insertToDoWithoutDueDate() {
        val expected = 0
        GlobalScope.launch {
            dao.insert(ToDo("1", "First Todo", null, false, now))
            dao.getDateCount(now).collect { actual ->
                assertEquals(expected, actual)
            }
        }
    }

    @After
    fun cleanup() {
        db.close()
    }
}