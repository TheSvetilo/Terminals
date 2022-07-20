package com.vbogd.terminals.presentation.screen.terminals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vbogd.terminals.domain.repository.TerminalsRepository
import javax.inject.Inject

class TerminalsViewModelFactory @Inject constructor(
    private val terminalsRepository: TerminalsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TerminalsViewModel::class.java)) {
            return TerminalsViewModel(terminalsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}