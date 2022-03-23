package com.example.simpletodoapp.data

import com.example.simpletodoapp.domain.model.SubTask
import com.example.simpletodoapp.domain.model.Task


fun TaskEntity.toTask(): Task {
    return Task(
        name = name,
        completion = completion,
        hasSubTask = hasSubTask,
        subTasks = emptyList(),
        taskId = taskId,
        id = id
    )
}

fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(
        name = name,
        completion = completion,
        hasSubTask = hasSubTask,
        taskId = taskId,
        id = id
    )
}

fun SubTaskEntity.toSubTask():SubTask{
    return SubTask(
        name = name,
        completion = completion,
        taskId = taskId,
        id = id
    )
}
fun SubTask.toSubTaskEntity():SubTaskEntity{
    return SubTaskEntity(
        name = name,
        completion = completion,
        taskId = taskId,
        id = id
    )
}
fun List<SubTask>.toSubTaskEntityList():List<SubTaskEntity>{
    val list: MutableList<SubTaskEntity> = mutableListOf()
    forEach {
          list.add(it.toSubTaskEntity())
    }
    return list
}

fun List<SubTaskEntity>.toSubTaskList():List<SubTask>{
    val list: MutableList<SubTask> = mutableListOf()
    forEach {
        list.add(it.toSubTask())
    }
    return list
}
