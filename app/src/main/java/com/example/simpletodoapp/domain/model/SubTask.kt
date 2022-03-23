package com.example.simpletodoapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubTask(
    val name: String,
    val completion: Boolean,
    val taskId:String,
    val id:Int? = null
):Parcelable
