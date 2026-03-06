package com.calleserpis.elfonavidenyo.domain.repository

import com.calleserpis.elfonavidenyo.domain.model.ElfAdventure
import kotlinx.coroutines.flow.Flow

interface ElfRepository {
    fun getElfAdventures(): Flow<List<ElfAdventure>>
    fun getFavouritesElfAdventures(): Flow<List<ElfAdventure>>
    fun getElfAdventureById(id: Int?): Flow<ElfAdventure?>
    suspend fun toggleFavorite(id: Int)
}