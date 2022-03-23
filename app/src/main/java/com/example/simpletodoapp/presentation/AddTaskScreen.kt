package com.example.simpletodoapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.PlusOne
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.simpletodoapp.domain.model.Task
import com.example.simpletodoapp.presentation.destinations.TaskScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun AddTaskScreen(
    navigator: DestinationsNavigator,
    task: Task?,
    listSize: Int
) {
    val viewModel = getViewModel<AddTaskScreenViewModel>()

    LaunchedEffect(key1 = true, block = {
        if (task != null){
            viewModel.onEdit(task)
        } else{
            viewModel.onInit(listSize)
        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.editTextState,
            onValueChange = {
                viewModel.onTaskTitleChanged(it)
            })
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.editSubTaskTitleState,
            onValueChange = {
                viewModel.onSubTaskTitleChanged(it)
            })
        Row() {
            IconButton(
                onClick = {
                    viewModel.onTaskIconButtonCLicked()
                    navigator.navigate(TaskScreenDestination)
                }) {
                Icon(
                    imageVector = Icons.Outlined.Done,
                    contentDescription = "done button"
                )
            }
            IconButton(
                onClick = {
                    viewModel.onSubTaskIconButtonClicked()
                }) {
                Icon(
                    imageVector = Icons.Outlined.PlusOne,
                    contentDescription = "adding one subTask"
                )
            }
        }

    }
}


























