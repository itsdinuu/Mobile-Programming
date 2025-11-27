package com.example.aplikasidinu.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasidinu.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUIState())
    val state: StateFlow<LoginUIState> = _state

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginUIState(isLoading = true)

            val result = loginUseCase(username, password)

            result.onSuccess { user ->
                _state.value = LoginUIState(
                    isLoading = false,
                    success = true,
                    message = "Selamat datang, ${user.name}"
                )
            }

            result.onFailure { e ->
                _state.value = LoginUIState(
                    isLoading = false,
                    success = false,
                    message = e.message ?: "Terjadi kesalahan"
                )
            }
        }
    }
}