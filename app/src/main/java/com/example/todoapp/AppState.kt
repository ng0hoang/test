package com.example.todoapp

data class AppState (
    var isClicked: Boolean = false,
    var listTask: MutableList<String> = mutableListOf(),
)