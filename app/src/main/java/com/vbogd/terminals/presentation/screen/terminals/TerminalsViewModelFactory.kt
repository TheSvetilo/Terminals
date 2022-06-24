package com.vbogd.terminals.presentation.screen.terminals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class TerminalsViewModelFactory(
    private val currentOrderId: String,
    private val orderDirection: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TerminalsViewModel::class.java)) {
            return TerminalsViewModel(currentOrderId, orderDirection) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}