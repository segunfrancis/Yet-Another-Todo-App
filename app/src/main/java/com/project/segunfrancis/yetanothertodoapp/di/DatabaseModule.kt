package com.project.segunfrancis.yetanothertodoapp.di

import android.content.Context
import androidx.room.Room
import com.project.segunfrancis.yetanothertodoapp.data.ToDoDao
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Created by SegunFrancis
 */

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ToDoRoomDatabase {
        return synchronized(ToDoRoomDatabase::class.java) {
            Room.databaseBuilder(
                context.applicationContext,
                ToDoRoomDatabase::class.java,
                "todo_database"
            )
                .build()
        }
    }

    @Provides
    fun provideToDoDao(database: ToDoRoomDatabase): ToDoDao {
        return database.todoDao()
    }

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}