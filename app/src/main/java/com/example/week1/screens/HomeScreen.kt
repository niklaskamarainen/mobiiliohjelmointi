package com.example.week1.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week1.model.Task
import com.example.week1.viewmodel.TaskViewModel
import com.example.week1.ui.theme.Week1Theme
import java.time.LocalDate
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: TaskViewModel,
    onTaskClick: (Int) -> Unit = {},
    onAddClick: () -> Unit = {},
    onNavigateCalendar: () -> Unit = {}
    ) {

    var newTaskTitle by remember { mutableStateOf("") }

    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()
    val addTaskFlag by viewModel.addTaskDialogVisible.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            title={Text("Tasks")},
            actions={
                IconButton(onClick = onNavigateCalendar) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Go to calendar"
                    )
                }
            }
        )

        Row(

        ) {

            Button(
                onClick = onAddClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Add Task")
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(tasks) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable{onTaskClick(task.id)}
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Checkbox(
                            checked = task.done,
                            onCheckedChange = {
                                viewModel.toggleDone(task.id)
                            }
                        )
                        Column {
                            Text(
                                text = task.title,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = task.description
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Button(
                            onClick = { viewModel.removeTask(task.id) },
                            modifier = Modifier.padding(end = 8.dp)
                            ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            Modifier.fillMaxWidth()
        ) {

            Button( onClick = {
                viewModel.filterByDone(true)
            }) {
                Text("Filter by done")
            }

            Button( onClick = {
             viewModel.sortByDueDate()
            }) {
                Text("Sort by date")
            }
        }

        Button( onClick = {
            viewModel.resetFilter()
        }) {
            Text("Clear filter")
        }

    }

    if(selectedTask != null){
        DetailDialog(
            task = selectedTask!!,
            onClose = {viewModel.closeDialog()},
            onUpdate = {viewModel.updateTask(it)},
            onDelete = {viewModel.removeTask(it)}
        )
    }

    if(addTaskFlag){
        AddDialog(
            onClose = { viewModel.addTaskDialogVisible.value = false },
            onAdd = { viewModel.addTask(it) }
        )
    }
}

@Preview(showBackground = true)

@Composable
fun HomeScreenPreview() {
    Week1Theme {

    }
}