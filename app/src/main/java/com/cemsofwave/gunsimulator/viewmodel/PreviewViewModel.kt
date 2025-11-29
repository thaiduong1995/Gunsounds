package com.cemsofwave.gunsimulator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cemsofwave.gunsimulator.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val installFirst = repository.installFirst

    fun setInstallFirst(isInstallFirst: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setInstallFirst(isInstallFirst)
        }
    }

}