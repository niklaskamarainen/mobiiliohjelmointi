package com.example.week1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week1.ui.theme.Week1Theme
import com.example.week1.domain.Task
import com.example.week1.domain.addTask
import com.example.week1.domain.mockTasks
import java.time.LocalDate
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.week1.domain.filterByDone
import com.example.week1.domain.sortByDueDate
import com.example.week1.domain.toggleDone



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week1Theme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    var allTasks by remember { mutableStateOf(mockTasks) }
    var visibleTasks by remember { mutableStateOf(mockTasks) }
    var newTaskTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Tehtävät",
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )


        visibleTasks.forEach { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = "${task.title} (${if (task.done) "done" else "todo"})"
                )

                Button( onClick = {
                    allTasks = toggleDone(allTasks, task.id)
                    visibleTasks = allTasks
                }) {
                    Text("Toggle done/todo")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newTaskTitle,
            onValueChange = {newTaskTitle = it},
            label = {Text("Uuden tehtävän nimi")},
            singleLine = true
        )

        Row() {
            Button(onClick = {
                val newTask = Task(
                    id = allTasks.size + 1,
                    title = newTaskTitle,
                    description = "Added from textField",
                    priority = 1,
                    dueDate = LocalDate.now(),
                    done = false
                )
                allTasks = addTask(allTasks, newTask)
                visibleTasks = allTasks
                newTaskTitle = ""
            }) {
                Text("Add Task")
            }

            Button( onClick = {
                visibleTasks = filterByDone(allTasks, done = true)
            }) {
                Text("Filter by done")
            }

            Button( onClick = {
                visibleTasks = sortByDueDate(visibleTasks)
            }) {
                Text("Sort by date")
            }
        }

        Button( onClick = {
            visibleTasks = allTasks
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