package com.project.segunfrancis.yetanothertodoapp.data

import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

interface ToDoRepository {

    fun getAllToDos(): Flow<List<ToDo>>

    suspend fun insert(toDo: ToDo)

    suspend fun toggleTodo(id: String)

    fun getUpcomingToDosCount(): Flow<Int>

    suspend fun delete(toDo: ToDo)
}