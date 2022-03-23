package com.example.simpletodoapp.domain.repository

import androidx.room.Query
import com.example.simpletodoapp.domain.model.SubTask
import com.example.simpletodoapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)

    fun getAllTask(hideCompleted:Boolean, searchQuery: String):Flow<List<Task>>

    suspend fun updateTask(task: Task)

    suspend fun insertSubTask(subTask: SubTask)

    suspend fun updateSubTask(subTask: SubTask)

    suspend fun deleteTaskSubTasks(task: Task)

    suspend fun deleteSubTask(subTask: SubTask)

    suspend fun getAllSubTask():List<SubTask>

    suspend fun getTaskSubtasks(taskId: String):List<SubTask>

}