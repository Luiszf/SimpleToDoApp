package com.example.simpletodoapp.di

import androidx.room.Room
import com.example.simpletodoapp.data.TaskDao
import com.example.simpletodoapp.data.TaskDatabase
import com.example.simpletodoapp.domain.repository.TaskRepository
import com.example.simpletodoapp.domain.repository.TaskRepositoryImpl
import com.example.simpletodoapp.presentation.AddTaskScreenViewModel
import com.example.simpletodoapp.presentation.TaskScreenViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {

    single<TaskDatabase> {
        Room.databaseBuilder(
            get(),
            TaskDatabase::class.java,
            "task_database").fallbackToDestructiveMigration()
            .build()
    }

    single<TaskRepository> {
        TaskRepositoryImpl(get<TaskDatabase>().taskDao, get<TaskDatabase>().subTaskDao)
    }

    viewModel { TaskScreenViewModel(get())}

    viewModel { AddTaskScreenViewModel(get()) }
}