package com.project.segunfrancis.yetanothertodoapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo ORDER BY created DESC")
    fun getAllToDos(): Flow<List<ToDo>>

    @Query("SELECT count(*) FROM todo WHERE completed = 0 AND dueDate >= :date")
    fun getDateCount(date: Long): Flow<Int>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getToDo(id: String): ToDo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(toDo: ToDo)

    @Query("UPDATE todo set completed = ~completed WHERE id = :id")
    suspend fun toggleTodo(id: String): Int

    @Delete
    suspend fun delete(toDo: ToDo)
}