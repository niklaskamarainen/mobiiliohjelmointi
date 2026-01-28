package com.example.week1.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.week1.model.Task

@Composable

fun DetailDialog(task: Task, onClose: () -> Unit, onUpdate: (Task) -> Unit, onDelete: (Int) -> Unit) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }

    AlertDialog(
        onDismissRequest = onClose,
        title = { Text("Edit task") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Row() {
                Button(onClick = {
                    onUpdate(
                        task.copy(
                            title = title,
                            description = description
                        )
                    )
                    onClose()
                }) {
                    Text("Save")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button( onClick = {
                    onDelete(task.id)
                    onClose()
                }) {
                    Text("Delete task")
                }
            }
        },
        dismissButton = {
            Button(onClick = onClose) {
                Text("Cancel")
            }
        },
    )
}