package com.project.segunfrancis.yetanothertodoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRepository
import com.project.segunfrancis.yetanothertodoapp.ui.add.AddViewModel
import com.project.segunfrancis.yetanothertodoapp.ui.list.ToDoListViewModel

/**
 * Created by SegunFrancis
 */

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val toDoRepository: ToDoRepository) :
    ViewModelProvider.Factory {
    /**
     * Creates a new instance of the given `Class`.
     *
     * @param modelClass a `Class` whose instance is requested
     * @param <T>        The type parameter for the ViewModel.
     * @return a newly created ViewModel
    </T> */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(ToDoListViewModel::class.java) ->
                    ToDoListViewModel(toDoRepository)
                isAssignableFrom(AddViewModel::class.java) ->
                    AddViewModel(toDoRepository)
                else ->
                    throw IllegalArgumentException("ViewModel class (${modelClass.name}) is not mapped")
            }
        } as T
}