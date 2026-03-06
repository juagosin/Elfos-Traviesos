package com.calleserpis.elfonavidenyo.ui.screens.detail

import com.calleserpis.elfonavidenyo.domain.model.ElfAdventure

data class DetailState(
    val id: Int? = null,
    val adventure: ElfAdventure? = null,
    val isEditing: Boolean = false,
    val isEdited: Boolean = false,
    val isLoading: Boolean = false,
    val isLoaded: Boolean = false,
    val error: String? = null
)