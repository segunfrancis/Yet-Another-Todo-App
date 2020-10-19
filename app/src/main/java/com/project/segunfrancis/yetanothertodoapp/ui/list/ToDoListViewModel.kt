package com.project.segunfrancis.yetanothertodoapp.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.project.segunfrancis.yetanothertodoapp.data.ToDo
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by SegunFrancis
 */

class ToDoListViewModel @ViewModelInject constructor(private val toDoRepository: ToDoRepository) :
    ViewModel() {

    private var _toDoList = MutableLiveData<List<ToDo>>()
    val toDoList: LiveData<List<ToDo>>
        get() = _toDoList

    private var _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    init {
        getToDos()
        getUpcomingToDosCount()
    }

    val upcomingToDosCount = toDoRepository.getUpcomingToDosCount()
    val allToDos = toDoRepository.getAllToDos()

    private fun getToDos() {
        viewModelScope.launch {
            toDoRepository.getAllToDos().collect {
                _toDoList.postValue(it)
            }
        }
    }

    private fun getUpcomingToDosCount() {
        viewModelScope.launch {
            toDoRepository.getUpcomingToDosCount().collect {
                _count.postValue(it)
            }
        }
    }

    fun toggleToDo(id: String) {
        toDoRepository.toggleTodo(id)
    }

    fun deleteToDo(toDo: ToDo) {
        toDoRepository.delete(toDo)
    }
}