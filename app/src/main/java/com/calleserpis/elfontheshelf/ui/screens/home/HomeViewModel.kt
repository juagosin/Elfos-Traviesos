package com.calleserpis.elfontheshelf.ui.screens.home

import android.util.Log.e
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calleserpis.elfontheshelf.domain.use_cases.ElfAdventuresUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val elfAdventuresUseCases: ElfAdventuresUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadElfAdventures()
    }

    private fun loadElfAdventures() {
        viewModelScope.launch {
            try{
                elfAdventuresUseCases.getElfAdventures().distinctUntilChanged().collect{
                    _state.update { currentState ->
                        currentState.copy(elfAdventures = it)
                    }
                }
            }catch (e: Exception){

            }
        }
    }
}