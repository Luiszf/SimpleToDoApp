package com.example.simpletodoapp.presentation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletodoapp.domain.model.SubTask
import com.example.simpletodoapp.domain.model.Task
import com.example.simpletodoapp.domain.repository.TaskRepository
import com.example.simpletodoapp.util.AppSettings
import com.example.simpletodoapp.util.AppSettingsSerializer
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

val Context.dataStore by dataStore("app-settings.json", AppSettingsSerializer)

class TaskScreenViewModel(
    private val repo: TaskRepository
) : ViewModel() {


    var taskName by mutableStateOf("")
        private set

    var searchString by mutableStateOf("")
        private set

    var hideCompleted by mutableStateOf(false)

    var tasks = repo.getAllTask(hideCompleted, searchString)

    fun getSubTasks(context: Context) = viewModelScope.launch{

        context.dataStore.data.collect {
            hideCompleted = it.hideCompleted
            searchString = it.searchQuery
        }

        tasks = repo.getAllTask(hideCompleted, searchString)
    }

    fun onCheckBoxChanged(context: Context) = viewModelScope.launch {
        hideCompleted = !hideCompleted
        context.dataStore.updateData {
            it.copy(hideCompleted = hideCompleted,)
        }
    }

    fun onSearchNameChanged(value: String,context: Context) = viewModelScope.launch {
        searchString = value

        context.dataStore.updateData {
            it.copy(searchQuery = searchString)
        }
    }

    fun onTaskNameChanged(value: String) = viewModelScope.launch {
        taskName = value
    }

    fun onTaskCheckedChanged(task: Task) = viewModelScope.launch {
        repo.updateTask(task = task.copy(completion = !task.completion))
    }


    fun onSubTaskCheckedChanged(subTask: SubTask,task: Task) = viewModelScope.launch {
        repo.updateSubTask(subTask.copy(completion = !subTask.completion))
        repo.updateTask(task)
    }

    fun onItemClicked(task: Task) = viewModelScope.launch {
        repo.deleteTask(task)

    }

    fun onArrowClicked(task: Task) = viewModelScope.launch {

    }

    fun savePreferences(context: Context) = viewModelScope.launch {

    }


}