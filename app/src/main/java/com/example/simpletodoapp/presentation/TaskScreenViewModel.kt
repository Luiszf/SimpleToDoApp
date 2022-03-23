package com.example.simpletodoapp.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletodoapp.domain.model.SubTask
import com.example.simpletodoapp.domain.model.Task
import com.example.simpletodoapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class TaskScreenViewModel(
    private val repo: TaskRepository
) : ViewModel() {

    var taskName by mutableStateOf("")
        private set

    var searchString by mutableStateOf("")
        private set

    var hideCompleted by mutableStateOf(false)

    var tasks = repo.getAllTask(hideCompleted, searchString)

    fun getSubTasks() = viewModelScope.launch{
        tasks = repo.getAllTask(hideCompleted, searchString)
    }

    fun onCheckBoxChanged() = viewModelScope.launch {
        hideCompleted = !hideCompleted
    }

    fun onSearchNameChanged(value: String) = viewModelScope.launch {
        searchString = value
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
        if (task.subTasks.isEmpty() && task.hasSubTask) {
           task.subTasks = repo.getTaskSubtasks(task.taskId)
        } else {
            task.subTasks = emptyList()
        }
    }


}