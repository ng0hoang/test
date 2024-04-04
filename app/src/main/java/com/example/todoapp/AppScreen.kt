package com.example.todoapp

import android.app.AlertDialog
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
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
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

        }
        Log.d("Test", "abc")
        if (appUiState.isClicked == true) {
            TaskDialog(
                userInput = appViewModel.userInput,
                onMakeTask = {appViewModel.makeTask(appViewModel.userInput)},
                onInputChange = { appViewModel.onInputChange(it) },
                turnOffDialog = { appViewModel.turnOffDialog() }
            )
        }
    }
}

@Composable
fun TaskDialog(
    userInput: String,
    onInputChange: (String) -> Unit,
    onMakeTask: () -> Unit,
    turnOffDialog: () -> Unit
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
        onDismissRequest = { turnOffDialog },
        confirmButton = {
            TextButton(
                onClick = {
                    onMakeTask
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    turnOffDialog
                }
            ) {
                Text("Cancel")
            }
        }
    )
}


