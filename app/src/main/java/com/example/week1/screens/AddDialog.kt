package com.example.week1.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week1.model.Task
import com.example.week1.viewmodel.TaskViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun AddDialog(
    viewModel: TaskViewModel = viewModel(),
    onClose: () -> Unit,
    onAdd: (Task) -> Unit
){
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("") }
    var task by remember {
        mutableStateOf(Task(id = 0, title = "", description = "", priority = 0, dueDate = "", done = false))
    }

    AlertDialog(
        onDismissRequest = onClose,
        title = { Text("Add Task") },
        text = {
            Column {
                OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
                OutlinedTextField(value = dueDate, onValueChange = { dueDate = it }, label = { Text("Due date (yyyy-MM-dd)") })
                OutlinedTextField(value = priority, onValueChange = { priority = it}, label = {Text("Priority (1-3)")})

            }
        },
        confirmButton = {
            Button(onClick = {
                task = Task(id = 0, title = title, description = description, priority = 0, dueDate = dueDate, done = false)
                onAdd(task)
                onClose()
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onClose) {
                Text("Cancel")
            }
        }
    )
}