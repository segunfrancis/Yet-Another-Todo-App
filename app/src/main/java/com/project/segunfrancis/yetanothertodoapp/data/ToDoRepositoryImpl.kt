package com.project.segunfrancis.yetanothertodoapp.data

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class ToDoRepositoryImpl @Inject constructor(private val toDoDao: ToDoDao) : ToDoRepository {

    override fun getAllToDos(): Flow<List<ToDo>> {
        return toDoDao.getAllToDos()
    }

    override fun insert(toDo: ToDo) {
        require(toDo.title != "") {
            "Title must not be empty"
        }
        GlobalScope.launch {
            toDoDao.insert(toDo)
        }
    }

    override fun toggleTodo(id: String) {
        GlobalScope.launch {
            require(toDoDao.toggleTodo(id) == 1) {
                "Todo not found"
            }
        }
    }

    override fun getUpcomingToDosCount(): Flow<Int> {
        return toDoDao.getDateCount(System.currentTimeMillis())
    }

    override fun delete(toDo: ToDo) {
        GlobalScope.launch { toDoDao.delete(toDo) }
    }
}