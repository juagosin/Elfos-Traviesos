package com.calleserpis.elfontheshelf.data.repository

import com.calleserpis.elfontheshelf.data.local.ElfAdventureDao
import com.calleserpis.elfontheshelf.data.mapper.toDomain
import com.calleserpis.elfontheshelf.domain.model.ElfAdventure
import com.calleserpis.elfontheshelf.domain.repository.ElfRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ElfRepositoryImpl(
    private val elfAdventureDao: ElfAdventureDao
) : ElfRepository {
    override fun getElfAdventures(): Flow<List<ElfAdventure>> {
        return elfAdventureDao.getElfAdventures().map { it.toDomain() }
    }

    override fun getFavouritesElfAdventures(): Flow<List<ElfAdventure>> {
        TODO("Not yet implemented")
    }

    override fun getElfAdventureById(id: Int?): Flow<ElfAdventure?> {
        return elfAdventureDao.getElfAdventureById(id).map { it?.toDomain() }

    }
}