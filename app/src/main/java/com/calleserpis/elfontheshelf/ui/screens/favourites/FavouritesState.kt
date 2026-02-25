package com.calleserpis.elfontheshelf.ui.screens.favourites

import com.calleserpis.elfontheshelf.domain.model.ElfAdventure

data class FavouritesState(
    val elfAdventures: List<ElfAdventure> = emptyList(),
    val allElfAdventures: List<ElfAdventure> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val isLoaded: Boolean = false,
    val error: String? = null
)

