package com.calleserpis.elfontheshelf.ui.screens.detail

import com.calleserpis.elfontheshelf.domain.model.ElfAdventure

data class DetailState(
    val adventure: ElfAdventure? = null,
    val isEditing: Boolean = false,
    val isEdited: Boolean = false,
    val isLoading: Boolean = false,
    val isLoaded: Boolean = false,
    val error: String? = null
)