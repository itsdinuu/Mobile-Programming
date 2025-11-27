package com.example.aplikasidinu.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasidinu.domain.RegisterUseCase.RegisterUseCase
import com.example.aplikasidinu.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val RegisterUseCase: RegisterUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUIState())
    val state: StateFlow<RegisterUIState> = _state

    fun register(username: String, full_name: String, date_of_birth: String, password: String) {
        viewModelScope.launch {
            _state.value = RegisterUIState(isLoading = true)

            val result = RegisterUseCase(username, full_name, date_of_birth, password)

            result.onSuccess { user ->
                _state.value = RegisterUIState(
                    isLoading = false,
                    success = true,
                    message = user
                )
            }

            result.onFailure { e ->
                _state.value = RegisterUIState(
                    isLoading = false,
                    success = false,
                    message = e.message ?: "Terjadi kesalahan"
                )
            }
        }
    }
}