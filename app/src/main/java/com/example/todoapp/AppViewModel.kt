package com.example.todoapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AppViewModel(): ViewModel() {

    private val _uiState = MutableStateFlow(AppState())

    val uiState: StateFlow<AppState> = _uiState.asStateFlow()


    var userInput by mutableStateOf("")
        private set

    fun addTask(currentList: MutableList<String>, task: String): MutableList<String> {
        currentList.add(task)
        return currentList
    }

    fun makeTask(task: String) {
        _uiState.update { currentState ->
            currentState.copy(
                listTask = addTask(currentState.listTask, task)
            )
        }
    }


    fun onInputChange(newInput: String) {
        userInput = newInput
    }

    fun newInput(newInput: String = "") {
        userInput = newInput
    }

    fun updateState() {
        _uiState.update { currentState ->
            currentState.copy(
                isClicked = true
            )
        }
    }

    fun turnOffDialog() {
        _uiState.update { currentState ->
            currentState.copy(
                isClicked = false
            )
        }
    }


}
    