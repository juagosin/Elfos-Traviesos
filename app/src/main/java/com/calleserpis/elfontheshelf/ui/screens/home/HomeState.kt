package com.calleserpis.elfontheshelf.ui.screens.home

import com.calleserpis.elfontheshelf.domain.model.ElfAdventure

data class HomeState(
    val elfAdventures: List<ElfAdventure> = emptyList(),
    val isLoading: Boolean = false,
    val isLoaded: Boolean = false,
    val error: String? = null
)