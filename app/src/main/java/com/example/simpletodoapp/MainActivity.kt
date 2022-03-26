package com.example.simpletodoapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.dataStore
import com.example.simpletodoapp.presentation.NavGraphs
import com.example.simpletodoapp.util.AppSettingsSerializer
import com.ramcosta.composedestinations.DestinationsNavHost




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.background
            ) {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }

        }
    }
}

