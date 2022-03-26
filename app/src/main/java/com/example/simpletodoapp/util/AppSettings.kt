package com.example.simpletodoapp.util

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val hideCompleted:Boolean = false,
    val searchQuery:String = ""
)
