package com.rakapermanaptr.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<EVENT, STATE, EFFECT>(
    initialState: STATE
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    protected val currentState get() = _state.value

    private val _effect = MutableSharedFlow<EFFECT>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: EVENT) {
        viewModelScope.launch { handleEvent(event) }
    }

    protected abstract suspend fun handleEvent(event: EVENT)

    protected fun setState(reducer: STATE.() -> STATE) {
        _state.update { it.reducer() }
    }

    protected fun setEffect(builder: () -> EFFECT) {
        viewModelScope.launch { _effect.emit(builder()) }
    }
}