package com.calleserpis.elfontheshelf.domain.repository

import com.calleserpis.elfontheshelf.domain.model.ElfAdventure
import kotlinx.coroutines.flow.Flow

interface ElfRepository {
    fun getElfAdventures(): Flow<List<ElfAdventure>>
    fun getFavouritesElfAdventures(): Flow<List<ElfAdventure>>
    fun getElfAdventureById(id: Int): Flow<ElfAdventure?>
}