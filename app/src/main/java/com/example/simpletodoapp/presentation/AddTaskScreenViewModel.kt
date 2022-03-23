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
import kotlinx.coroutines.launch

class AddTaskScreenViewModel(
    private val repo: TaskRepository
) : ViewModel() {

    var editTextState by mutableStateOf("")
        private set

    var editSubTaskTitleState by mutableStateOf("")
        private set

    private val subTaskList: MutableList<SubTask> = mutableListOf()

    private var hasSubTask = false

    lateinit var realTask:Task

    fun onInit(i: Int) = viewModelScope.launch {
        val int = i + 1
        hasSubTask = false
        realTask = Task(
            name = "",
            completion = false,
            hasSubTask = false,
            subTasks = emptyList(),
            taskId = int.toString()
        )
    }
    fun onEdit(task: Task) = viewModelScope.launch {
        editTextState = task.name
        realTask = task
        subTaskList.addAll(task.subTasks)
        hasSubTask = task.hasSubTask
    }

    fun onTaskTitleChanged(text: String){
        editTextState = text
    }

    fun onSubTaskTitleChanged(text: String){
        editSubTaskTitleState = text
    }

    fun onTaskIconButtonCLicked() = viewModelScope.launch {
        if(editTextState.isBlank()) return@launch
        val task = realTask.copy(name = editTextState, hasSubTask = hasSubTask, subTasks = subTaskList)
        repo.insertTask(task)
    }

    fun onSubTaskIconButtonClicked() = viewModelScope.launch{
        hasSubTask = true
        subTaskList.add(SubTask(editSubTaskTitleState, false, taskId = realTask.taskId))
        editSubTaskTitleState = ""
    }
}
