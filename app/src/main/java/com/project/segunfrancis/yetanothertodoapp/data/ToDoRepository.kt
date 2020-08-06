package com.project.segunfrancis.yetanothertodoapp.data

import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

interface ToDoRepository {

    fun getAllToDos(): Flow<List<ToDo>>

    fun insert(toDo: ToDo)

    fun toggleTodo(id: String)

    fun getUpcomingToDosCount(): Flow<Int>

    suspend fun delete(toDo: ToDo)
}