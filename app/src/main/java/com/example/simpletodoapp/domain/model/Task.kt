package com.example.simpletodoapp.domain.model

import android.os.Parcelable
import kotlinx.coroutines.flow.Flow
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task (
    val name:String,
    val completion:Boolean,
    val hasSubTask:Boolean,
    var subTasks: List<SubTask>,
    val taskId: String,
    val id:Int? = null
):Parcelable
