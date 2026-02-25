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

    fun onFavoriteClick(adventureId: Int) {
        viewModelScope.launch {
            try {
                elfAdventuresUseCases.toggleFavorite(adventureId)
            } catch (e: Exception) {
                e("HomeViewModel", "Error toggling favorite", e)
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _state.update { currentState ->
            val filtered = filterAdventures(
                adventures = currentState.allElfAdventures,
                query = query
            )
            currentState.copy(
                searchQuery = query,
                elfAdventures = filtered
            )
        }
    }

    private fun loadElfAdventures() {
        viewModelScope.launch {
            try{
                elfAdventuresUseCases.getElfAdventures().distinctUntilChanged().collect{
                    _state.update { currentState ->
                        val filtered = filterAdventures(
                            adventures = it,
                            query = currentState.searchQuery
                        )
                        currentState.copy(
                            allElfAdventures = it,
                            elfAdventures = filtered
                        )
                    }
                }
            }catch (e: Exception){

            }
        }
    }

    private fun filterAdventures(
        adventures: List<com.calleserpis.elfontheshelf.domain.model.ElfAdventure>,
        query: String
    ): List<com.calleserpis.elfontheshelf.domain.model.ElfAdventure> {
        if (query.isBlank()) return adventures

        return adventures.filter { adventure ->
            adventure.title.contains(query, ignoreCase = true) ||
                    adventure.tags.any { tag ->
                        tag.label.contains(query, ignoreCase = true)
                    } ||
                    adventure.description.contains(query, ignoreCase = true)

        }
    }
}