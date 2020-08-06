package com.project.segunfrancis.yetanothertodoapp.util

import com.project.segunfrancis.yetanothertodoapp.data.ToDo
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Created by SegunFrancis
 */

class ToDoTestRepository(private val toDos: ArrayList<ToDo>) : ToDoRepository {
    override fun getAllToDos(): Flow<List<ToDo>> {
        return flowOf(toDos)
    }

    override fun insert(toDo: ToDo) {
        TODO("Not yet implemented")
    }

    override fun toggleTodo(id: String) {
        TODO("Not yet implemented")
    }

    override fun getUpcomingToDosCount(): Flow<Int> {
        val count = toDos.count {
            !it.completed && it.dueDate != null && it.dueDate!! >= System.currentTimeMillis()
        }
        return flowOf(count)
    }

    override suspend fun delete(toDo: ToDo) {
        TODO("Not yet implemented")
    }
}