package com.example.simpletodoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubTaskEntity(
    val name: String,
    val completion: Boolean,
    val taskId:String,
    @PrimaryKey val id: Int? = null
)
