package com.example.week1.domain

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class TaskViewModel : ViewModel() {
    private var allTasks = listOf<Task>()
    var tasks by mutableStateOf(listOf<Task>())
        private set

    init {
        allTasks = listOf(
            Task(1, "Osta maitoa", "Käy kaupassa", 1, LocalDate.now().plusDays(1), false),
            Task(2, "Käytä koira", "Menen lenkille koiran kanssa", 1, LocalDate.now(), false),
            Task(3, "Salille", "Tee rintatreeni", 2, LocalDate.now().plusDays(1), false),
            Task(4, "Kolaus", "Kolaa piha", 1, LocalDate.now(), true),
            Task(5, "Kouluun", "Mene kouluun luennolle", 1, LocalDate.now().plusDays(2), false)
        )
        tasks = allTasks
    }

    fun addTask(task: Task) {
        tasks = tasks + task
    }


    fun toggleDone(id: Int) {
        tasks = tasks.map { task ->
            if (task.id == id) task.copy(done = !task.done)
            else task
        }
    }


    fun filterByDone(done: Boolean){
        tasks = tasks.filter {it.done == done}
    }


    fun sortByDueDate(){
        tasks = tasks.sortedBy { it.dueDate }
    }

    fun removeTask(id: Int){
        tasks = tasks.filter {it.id != id}
    }

    fun resetFilter(){
        tasks = allTasks
    }


}