package com.example.simpletodoapp.data

import android.text.BoringLayout
import androidx.room.*
import com.example.simpletodoapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM taskentity WHERE (completion != :hideCompleted OR completion = 0) AND name LIKE '%' || :searchQuery || '%' ")
    fun getTasks(hideCompleted: Boolean, searchQuery: String):Flow<List<TaskEntity>>

    @Update
    suspend fun updateTask(Task:TaskEntity)


}