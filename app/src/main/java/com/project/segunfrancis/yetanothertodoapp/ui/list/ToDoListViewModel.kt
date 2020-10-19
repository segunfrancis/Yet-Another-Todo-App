package com.project.segunfrancis.yetanothertodoapp.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.project.segunfrancis.yetanothertodoapp.asLiveData
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
    val toDoList: LiveData<List<ToDo>> = _toDoList.asLiveData()

    private var _count = MutableLiveData<Int>()
    val count: LiveData<Int> = _count.asLiveData()

    val allToDos = toDoRepository.getAllToDos()
    val upcomingToDosCount = toDoRepository.getUpcomingToDosCount()

    init {
        getToDos()
        getUpcomingToDosCount()
    }

    private fun getToDos() {
        viewModelScope.launch {
            allToDos.collect {
                _toDoList.postValue(it)
            }
        }
    }

    private fun getUpcomingToDosCount() {
        viewModelScope.launch {
            upcomingToDosCount.collect {
                _count.postValue(it)
            }
        }
    }

    fun toggleToDo(id: String) {
        viewModelScope.launch {
            toDoRepository.toggleTodo(id)
        }
    }

    fun deleteToDo(toDo: ToDo) {
        viewModelScope.launch {
            toDoRepository.delete(toDo)
        }
    }
}