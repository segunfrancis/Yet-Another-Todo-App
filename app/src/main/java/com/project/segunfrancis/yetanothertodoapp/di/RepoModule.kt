package com.project.segunfrancis.yetanothertodoapp.di

import com.project.segunfrancis.yetanothertodoapp.data.ToDoRepository
import com.project.segunfrancis.yetanothertodoapp.data.ToDoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by SegunFrancis
 */

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: ToDoRepositoryImpl): ToDoRepository

}