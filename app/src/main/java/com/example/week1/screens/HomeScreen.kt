package com.example.week1.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import com.example.week1.domain.Task
import com.example.week1.domain.TaskViewModel
import com.example.week1.domain.mockTasks
import com.example.week1.ui.theme.Week1Theme
import java.time.LocalDate
import androidx.compose.foundation.lazy.items



@Composable
fun HomeScreen(viewModel: TaskViewModel = viewModel()) {

    var newTaskTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Tasks",
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(

        ) {
            OutlinedTextField(
                value = newTaskTitle,
                onValueChange = {newTaskTitle = it},
                label = {Text("Name of task")},
                singleLine = true
            )
            Button(onClick = {
                val newTask = Task(
                    id = viewModel.tasks.size + 1,
                    title = newTaskTitle,
                    description = "Added from textField",
                    priority = 1,
                    dueDate = LocalDate.now(),
                    done = false
                )
                viewModel.addTask(newTask)
                newTaskTitle = ""
            }) {
                Text("Add Task")
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(viewModel.tasks) { task ->
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
                    Text(
                        text = task.title,
                        modifier = Modifier.weight(1f)
                    )
                    Button(onClick = {
                        viewModel.removeTask(task.id)
                    }) {
                        Text("Delete")
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
}

@Preview(showBackground = true)

@Composable
fun HomeScreenPreview() {
    Week1Theme {
        HomeScreen()
    }
}