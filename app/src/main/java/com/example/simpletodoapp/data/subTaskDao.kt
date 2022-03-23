package com.example.simpletodoapp.data

import androidx.room.*
import com.example.simpletodoapp.domain.model.SubTask
import kotlinx.coroutines.flow.Flow

@Dao
interface subTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubTask(subTaskEntity: SubTaskEntity)

    @Delete
    suspend fun deleteSubTask(subTaskEntity: SubTaskEntity)

    @Update
    suspend fun updateSubTask(subTaskEntity: SubTaskEntity)

    @Query("DELETE FROM subtaskentity WHERE taskId = :taskId")
    suspend fun deleteTaskSubtasks(taskId: String)

    @Query("SELECT * FROM subtaskentity")
    suspend fun getSubTasks(): List<SubTaskEntity>

    @Query("SELECT * FROM SubTaskEntity WHERE taskId = :taskId")
    suspend fun getTaskSubTasks(taskId: String): List<SubTaskEntity>
}