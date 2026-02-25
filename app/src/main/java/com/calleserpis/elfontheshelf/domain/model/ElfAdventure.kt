package com.calleserpis.elfontheshelf.domain.model

import androidx.compose.ui.graphics.Color


data class ElfAdventure (
    val id: Int,
    val title: String,
    val imageRes: String,
    val tags: List<ElfTag>,
    val description: String,
    val isFavorite: Boolean = false
)
data class ElfTag(
    val label: String,
    val colortype: Long
)