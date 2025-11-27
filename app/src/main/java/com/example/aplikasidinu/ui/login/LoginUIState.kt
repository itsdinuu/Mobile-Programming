package com.example.aplikasidinu.ui.login

data class LoginUIState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val success: Boolean = false
)