package com.project.segunfrancis.yetanothertodoapp

import android.app.Activity
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

/**
 * Created by SegunFrancis
 */

fun <T : ViewModel> Activity.obtainViewModel(viewModelClass: Class<T>): T {
    val toDoRepository = (this.application as ToDoApplication).toDoRepository
    return ViewModelProvider(this as ViewModelStoreOwner, ViewModelFactory(toDoRepository)).get(
        viewModelClass
    )
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}