package com.project.segunfrancis.yetanothertodoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by SegunFrancis
 */

@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class ToDoRoomDatabase : RoomDatabase() {
    abstract fun todoDao(): ToDoDao

    companion object {
        private var todoRoomInstance: ToDoRoomDatabase? = null

        fun getInstance(context: Context): ToDoRoomDatabase? {
            if (todoRoomInstance == null) {
                synchronized(ToDoRoomDatabase::class.java) {
                    if (todoRoomInstance == null) {
                        todoRoomInstance = Room.databaseBuilder<ToDoRoomDatabase>(
                            context.applicationContext,
                            ToDoRoomDatabase::class.java,
                            "todo_database"
                        )
                            .build()
                    }
                }
            }
            return todoRoomInstance
        }
    }
}