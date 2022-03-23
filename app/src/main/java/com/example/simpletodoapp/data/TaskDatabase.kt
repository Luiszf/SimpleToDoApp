package com.example.simpletodoapp.data

import android.media.SubtitleData
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        TaskEntity::class,
        SubTaskEntity::class],
    version = 1
)
abstract class TaskDatabase:RoomDatabase(){
    abstract val taskDao:TaskDao
    abstract val subTaskDao:subTaskDao
}