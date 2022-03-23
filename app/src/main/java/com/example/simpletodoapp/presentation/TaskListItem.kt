package com.example.simpletodoapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpletodoapp.domain.model.SubTask
import com.example.simpletodoapp.domain.model.Task

@Composable
fun TaskListItem(
    task: Task,
    onTaskCheckBoxClicked: (Boolean) -> Unit = {},
    onSubTaskCheckBoxClicked: (SubTask) -> Unit = {},
    onSettingsButtonClicked: () -> Unit = {},
    onArrowButtonClicked: () -> Unit = {},
    onItemClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
        Row(
            modifier.clickable { onItemClicked() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = task.name,
                fontSize = 24.sp
            )
            Row {
                IconButton(onClick = {
                    onArrowButtonClicked
                }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowDownward,
                        contentDescription = "delete button"
                    )
                }
                Checkbox(
                    checked = task.completion,
                    onCheckedChange = onTaskCheckBoxClicked
                )
                IconButton(onClick = onSettingsButtonClicked) {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = "delete button"
                    )
                }
            }

        }
        task.subTasks.forEachIndexed { index, subTask ->
            Row(
                modifier.clickable { onItemClicked() }
                    .offset(x = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = subTask.name,
                    fontSize = 24.sp
                )
                Row {
                    Checkbox(
                        checked = subTask.completion,
                        onCheckedChange = {
                            onSubTaskCheckBoxClicked(subTask)
                        }
                    )
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "delete button"
                        )
                    }
                }
            }
        }
}