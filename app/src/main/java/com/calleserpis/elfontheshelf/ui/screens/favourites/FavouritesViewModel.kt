package com.calleserpis.elfontheshelf.ui.screens.favourites

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
class FavouritesViewModel @Inject constructor(
    private val elfAdventuresUseCases: ElfAdventuresUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(FavouritesState())
    val state: StateFlow<FavouritesState> = _state.asStateFlow()

    init {
        loadFavouriteElfAdventures()
    }

    fun onFavoriteClick(adventureId: Int) {
        viewModelScope.launch {
            try {
                elfAdventuresUseCases.toggleFavorite(adventureId)
            } catch (ex: Exception) {
                e("FavouritesViewModel", "Error toggling favorite", ex)
            }
        }
    }

    private fun loadFavouriteElfAdventures() {
        viewModelScope.launch {
            try {
                elfAdventuresUseCases.getFavouriteElfAdventures()
                    .distinctUntilChanged()
                    .collect { favourites ->
                        _state.update { currentState ->
                            currentState.copy(elfAdventures = favourites)
                        }
                    }
            } catch (ex: Exception) {
                e("FavouritesViewModel", "Error loading favourites", ex)
            }
        }
    }
}

