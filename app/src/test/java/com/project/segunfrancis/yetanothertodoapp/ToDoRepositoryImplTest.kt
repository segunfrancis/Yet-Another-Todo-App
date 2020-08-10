package com.project.segunfrancis.yetanothertodoapp

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRepositoryImpl
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRoomDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.spy
import org.robolectric.annotation.Config

/**
 * Created by SegunFrancis
 */

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ToDoRepositoryImplTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private val now = System.currentTimeMillis()
    private val day = 1000 * 60 * 60 * 24

    private lateinit var db: ToDoRoomDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, ToDoRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Test
    fun test_getUpcomingToDoCountEmpty() {
        val dao = spy(db.todoDao())
        val repo = ToDoRepositoryImpl(dao)
        val expected = 0

        var actual = 0
        runBlockingTest {
            repo.getUpcomingToDosCount().collect {
                actual = it
            }
        }
        assertEquals(expected, actual)
        //verify(dao).getDateCount(any())
    }

    @Test
    fun test_getUpcomingToDoCountSingleMatch() {
        val dao = spy(db.todoDao())
        val repo = ToDoRepositoryImpl(dao)
        val expected = 0

        var actual = 0
        runBlockingTest {
            repo.getUpcomingToDosCount().collect {
                actual = it
            }
        }
        assertEquals(expected, actual)
        verify(dao).getDateCount(any())
    }

    @After
    fun teardown() {
        db.close()
    }
}