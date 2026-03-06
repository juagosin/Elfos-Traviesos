package com.calleserpis.elfonavidenyo.data.repository

import com.calleserpis.elfonavidenyo.data.local.ElfAdventureDao
import com.calleserpis.elfonavidenyo.data.mapper.toDomain
import com.calleserpis.elfonavidenyo.domain.model.ElfAdventure
import com.calleserpis.elfonavidenyo.domain.repository.ElfRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ElfRepositoryImpl(
    private val elfAdventureDao: ElfAdventureDao
) : ElfRepository {
    override fun getElfAdventures(): Flow<List<ElfAdventure>> {
        return elfAdventureDao.getElfAdventures().map { it.toDomain() }
    }

    override fun getFavouritesElfAdventures(): Flow<List<ElfAdventure>> {
        return elfAdventureDao.getFavouriteElfAdventures().map { it.toDomain() }
    }

    override fun getElfAdventureById(id: Int?): Flow<ElfAdventure?> {
        return elfAdventureDao.getElfAdventureById(id).map { it?.toDomain() }

    }

    override suspend fun toggleFavorite(id: Int) {
        elfAdventureDao.toggleFavorite(id)
    }
}