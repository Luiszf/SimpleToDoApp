package com.example.simpletodoapp.presentation

import android.util.Log
import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpletodoapp.domain.model.SubTask
import com.example.simpletodoapp.domain.model.Task
import com.example.simpletodoapp.presentation.destinations.AddTaskScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@Destination(start = true)
@Composable
fun TaskScreen(
    navigator: DestinationsNavigator,
) {

    val viewModel = getViewModel<TaskScreenViewModel>()

    val taskList = viewModel.tasks.collectAsState(initial = emptyList()).value


    LaunchedEffect(key1 = taskList){
        Log.i("TAG", "TaskScreen: $taskList")
        viewModel.getSubTasks()
    }

    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            Arrangement.SpaceBetween
        ) {
            Row(Modifier.fillMaxWidth()) {
                TextField(value = viewModel.searchString, onValueChange = {
                    viewModel.onSearchNameChanged(it)
                })
                Checkbox(
                    checked = viewModel.hideCompleted,
                    onCheckedChange = { viewModel.onCheckBoxChanged()
                    })
            }

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(taskList) { taskItem ->
                    TaskListItem(
                        task = taskItem,
                        modifier = Modifier.fillMaxWidth(),
                        onTaskCheckBoxClicked = {
                            viewModel.onTaskCheckedChanged(taskItem)
                        },
                        onSubTaskCheckBoxClicked = {
                            viewModel.onSubTaskCheckedChanged(it, taskItem)
                        },
                        onSettingsButtonClicked = {
                            navigator.navigate(AddTaskScreenDestination(taskItem, taskList.size))
                        },
                        onItemClicked = {
                            viewModel.onItemClicked(taskItem)
                        },
                        onArrowButtonClicked = {
                            viewModel.onArrowClicked(taskItem)
                        }
                    )
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                Arrangement.Center
            ) {
                IconButton(onClick = {
                    navigator.navigate(AddTaskScreenDestination(null, taskList.size))
                }) {
                    Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add task Button")
                }
            }
        }
    }
}










