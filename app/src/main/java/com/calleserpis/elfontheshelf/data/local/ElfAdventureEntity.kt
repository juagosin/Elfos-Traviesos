package com.calleserpis.elfontheshelf.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "elf_adventures")
data class ElfAdventureEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val imageRes: Int,
    val tags: String,
    val description: String,
    val isFavorite: Boolean = false
)
