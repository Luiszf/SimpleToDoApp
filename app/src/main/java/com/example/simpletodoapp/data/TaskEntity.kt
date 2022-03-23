package com.example.simpletodoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity (
    val name:String,
    val completion: Boolean,
    val hasSubTask:Boolean,
    val taskId:String,
    @PrimaryKey val id : Int? = null
)

