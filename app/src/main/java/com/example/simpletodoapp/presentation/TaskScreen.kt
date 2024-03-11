package com.example.simpletodoapp.presentation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.dataStore
import com.example.simpletodoapp.presentation.destinations.AddTaskScreenDestination
import com.example.simpletodoapp.util.AppSettingsSerializer
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel


@Destination(start = true)
@Composable
fun TaskScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel = getViewModel<TaskScreenViewModel>()

    val taskList = viewModel.tasks.collectAsState(initial = emptyList()).value

    val context = get<Context>()


    LaunchedEffect(key1 = taskList) {
        Log.i("TAG", "TaskScreen: $taskList")
        viewModel.getSubTasks(context)
    }

    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            Arrangement.SpaceBetween
        ) {
            Row(Modifier.fillMaxWidth()) {
                TextField(
                    value = viewModel.searchString
                    , onValueChange = {
                    viewModel.onSearchNameChanged(it, context)
                })
                Checkbox(
                    checked = viewModel.hideCompleted,
                    onCheckedChange = {
                        viewModel.onCheckBoxChanged(context)
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










