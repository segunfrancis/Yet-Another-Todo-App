package com.project.segunfrancis.yetanothertodoapp

import android.app.Application
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRepository
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRepositoryImpl
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRoomDatabase

/**
 * Created by SegunFrancis
 */

class ToDoApplication : Application() {

    val toDoRepository: ToDoRepository
        get() = ToDoRepositoryImpl(
            ToDoRoomDatabase.getInstance(this.applicationContext)!!.todoDao()
        )
}