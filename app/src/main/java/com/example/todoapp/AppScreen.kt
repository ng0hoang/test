package com.example.todoapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    appViewModel: AppViewModel = viewModel()
) {

    val appUiState by appViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text ("Todo App") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(255, 224, 179),
                    titleContentColor = Color.Black
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { appViewModel.updateState() },
                containerColor = Color(255, 179, 255)
            ) {
                Row {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add",
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Add task",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(top = 8.dp, end = 8.dp),

                        )
                }
            }
        }
    ) {innerPadding ->
        ShowListTask(listTask = appUiState.listTask, innerPadding, modifier = Modifier.padding(top = 72.dp))
        if (appUiState.isClicked) {
            TaskDialog(
                userInput = appViewModel.userInput,
                onMakeTask = { appViewModel.makeTask(appViewModel.userInput) },
                onInputChange = { appViewModel.onInputChange(it) },
                turnOffDialog = { appViewModel.turnOffDialog() },
                newInput = { appViewModel.newInput() }
            )
        }
    }
}

@Composable
fun TaskDialog(
    userInput: String,
    onInputChange: (String) -> Unit,
    onMakeTask: () -> Unit,
    turnOffDialog: () -> Unit,
    newInput: () -> Unit
) {
    AlertDialog(
        title = {
                Text(text = "Nhap task can lam:")
        },
        text = {
               OutlinedTextField(
                   value = userInput,
                   onValueChange = onInputChange
               )
        },
        onDismissRequest = {
            turnOffDialog()
            newInput()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onMakeTask()
                    turnOffDialog()
                    newInput()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    turnOffDialog()
                    newInput()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ShowTask(task: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .size(32.dp)
    ) {
        Text(text = task)
    }
}

@Composable
fun ShowListTask(listTask: MutableList<String>, padding: PaddingValues, modifier: Modifier) {
    Column (modifier = modifier){
        for (task in listTask) {
            ShowTask(task = task)
        }
    }
}






