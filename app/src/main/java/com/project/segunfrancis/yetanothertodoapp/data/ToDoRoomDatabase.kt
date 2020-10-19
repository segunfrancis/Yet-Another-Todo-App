package com.project.segunfrancis.yetanothertodoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by SegunFrancis
 */

@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class ToDoRoomDatabase : RoomDatabase() {
    abstract fun todoDao(): ToDoDao
}