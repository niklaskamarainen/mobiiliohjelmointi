package com.example.week1.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.week1.model.Task
import java.time.LocalDate
import kotlin.collections.plus
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.example.week1.model.mockTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskViewModel : ViewModel() {

    private val _allTasks = MutableStateFlow(mockTasks)
    private val _tasks = MutableStateFlow(_allTasks.value)
    val tasks: StateFlow<List<Task>> = _tasks

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask

    fun selectedTask(task: Task) {
        _selectedTask.value = task
    }
    fun closeDialog() {
        _selectedTask.value = null
    }

    val addTaskDialogVisible = MutableStateFlow<Boolean>(false)

    fun openTask(id: Int){
        val task = _tasks.value.find {it.id == id}
        _selectedTask.value = task
    }

    fun addTask(task: Task) {
        _allTasks.value += task
        _tasks.value = _allTasks.value
    }


    fun toggleDone(id: Int) {
        _allTasks.value = _allTasks.value.map{
            if(it.id == id) it.copy(done = !it.done) else it
        }
        _tasks.value = _allTasks.value
    }


    fun filterByDone(done: Boolean){
        _tasks.value = _allTasks.value.filter { it.done == done }
    }


    fun sortByDueDate(){
        _tasks.value = _allTasks.value.sortedBy { it.dueDate.ifBlank { "9999-12-31" } }
    }

    fun removeTask(id: Int){
        _allTasks.value = _allTasks.value.filter { it.id != id }
        _tasks.value = _allTasks.value
    }

    fun resetFilter(){
        _tasks.value = _allTasks.value
    }

    fun updateTask(updatedTask: Task){
        _allTasks.value = _allTasks.value.map {
            if (it.id == updatedTask.id) updatedTask else it
        }
        _tasks.value = _allTasks.value
    }


}