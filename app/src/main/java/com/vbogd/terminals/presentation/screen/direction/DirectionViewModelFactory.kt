package com.vbogd.terminals.presentation.screen.direction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DirectionViewModelFactory(
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DirectionViewModel::class.java)) {
            return DirectionViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}