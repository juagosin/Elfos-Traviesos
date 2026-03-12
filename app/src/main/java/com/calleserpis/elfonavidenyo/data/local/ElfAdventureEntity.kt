package com.calleserpis.elfonavidenyo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "elf_adventures")
data class ElfAdventureEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val title_en: String? = null,
    val imageRes: String,
    val tags: String,
    val description: String,
    val description_en: String? = null,
    val isFavorite: Boolean = false
)
