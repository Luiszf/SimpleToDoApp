package com.example.simpletodoapp.domain.repository

import com.example.simpletodoapp.data.*
import com.example.simpletodoapp.domain.model.SubTask
import com.example.simpletodoapp.domain.model.Task
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class TaskRepositoryImpl(
    private val taskDao: TaskDao,
    private val subTaskDao: subTaskDao
) : TaskRepository {

    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task.toTaskEntity())
        task.subTasks.forEach {
            subTaskDao.insertSubTask(it.toSubTaskEntity())
        }
    }

    override suspend fun deleteTask(task: Task) {
        subTaskDao.deleteTaskSubtasks(task.taskId)
        taskDao.deleteTask(task.toTaskEntity())
    }

    override fun getAllTask(hideCompleted: Boolean, searchQuery: String): Flow<List<Task>> {
        val tasks = taskDao.getTasks(hideCompleted, searchQuery).map { tasks ->
            tasks.map {
                it.toTask()
            }
        }.map {
            it.map { task->
                val subtask = mutableListOf<SubTask>()
                val list = subTaskDao.getTaskSubTasks(task.taskId).map { it.toSubTask() }

                subtask.addAll(list)
                task.copy(subTasks = subtask)
            }
        }

        return tasks
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(task.toTaskEntity())
    }

    override suspend fun insertSubTask(subTask: SubTask) {
        subTaskDao.insertSubTask(subTask.toSubTaskEntity())
    }

    override suspend fun updateSubTask(subTask: SubTask) {
        subTaskDao.updateSubTask(subTask.toSubTaskEntity())
    }


    override suspend fun deleteTaskSubTasks(task: Task) {
        subTaskDao.deleteTaskSubtasks(task.taskId)
    }

    override suspend fun deleteSubTask(subTask: SubTask) {
        subTaskDao.deleteSubTask(subTask.toSubTaskEntity())
    }

    override suspend fun getAllSubTask(): List<SubTask> {
        return subTaskDao.getSubTasks().map {  it.toSubTask()  }
    }

    override suspend fun getTaskSubtasks(taskId: String):List<SubTask> {
        return subTaskDao.getTaskSubTasks(taskId).map { it.toSubTask()  }
    }


}