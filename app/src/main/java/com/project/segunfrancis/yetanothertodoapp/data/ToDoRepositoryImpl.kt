package com.project.segunfrancis.yetanothertodoapp.data

import kotlinx.coroutines.flow.Flow
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class ToDoRepositoryImpl @Inject constructor(private val toDoDao: ToDoDao) : ToDoRepository {

    override fun getAllToDos(): Flow<List<ToDo>> {
        return toDoDao.getAllToDos()
    }

    override suspend fun insert(toDo: ToDo) {
        require(toDo.title != "") {
            "Title must not be empty"
        }
        toDoDao.insert(toDo)
    }

    override suspend fun toggleTodo(id: String) {
        require(toDoDao.toggleTodo(id) == 1) {
            throw IllegalArgumentException("Todo not found")
        }
    }

    override fun getUpcomingToDosCount(): Flow<Int> {
        return toDoDao.getDateCount(System.currentTimeMillis())
    }

    override suspend fun delete(toDo: ToDo) {
        toDoDao.delete(toDo)
    }
}