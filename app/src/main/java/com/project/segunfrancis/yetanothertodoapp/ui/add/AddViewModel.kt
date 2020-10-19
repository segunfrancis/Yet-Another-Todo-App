package com.project.segunfrancis.yetanothertodoapp.ui.add

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.yetanothertodoapp.data.ToDo
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRepository
import kotlinx.coroutines.launch
import java.util.*

/**
 * Created by SegunFrancis
 */

class AddViewModel @ViewModelInject constructor(private val toDoRepository: ToDoRepository) :
    ViewModel() {
    val toDo = ToDo(
        UUID.randomUUID().toString(),
        "",
        null,
        false,
        0
    )

    fun save(): String? {
        if (toDo.title == "") return "Title is required"

        toDo.created = System.currentTimeMillis()
        viewModelScope.launch { toDoRepository.insert(toDo) }
        return null
    }
}