package com.calleserpis.elfonavidenyo.ui.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calleserpis.elfonavidenyo.domain.use_cases.ElfAdventuresUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val adventuresUseCases: ElfAdventuresUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    init {
        //Cargamos el detalle de la aventura
        loadAdventure()
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.LoadAdventure -> {
                loadAdventure(event.adventureId)
            }

            is DetailEvent.OnFavoriteClick -> {
                toggleFavorite(event.adventureId)
            }

            else -> {}
        }
    }

    private fun loadAdventure(adventureId: Int? = null) {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {

            try {
                adventuresUseCases.getElfAdventureById(adventureId).collect { adventureDetail ->
                    adventureDetail.let {
                        _state.update { it.copy(adventure = adventureDetail) }
                        Log.e("DetailScreenViewModel", "adventure :${adventureDetail}")
                    }

                }

            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
                Log.e("DetailScreenViewModel", "Error :", e)
            }
            _state.update { it.copy(isLoading = true) }
        }


    }

    private fun toggleFavorite(adventureId: Int) {
        viewModelScope.launch {
            try {
                adventuresUseCases.toggleFavorite(adventureId)
            } catch (e: Exception) {
                Log.e("DetailScreenViewModel", "Error toggling favorite", e)
            }
        }
    }

}

