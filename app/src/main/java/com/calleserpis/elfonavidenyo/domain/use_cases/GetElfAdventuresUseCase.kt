package com.calleserpis.elfonavidenyo.domain.use_cases

import com.calleserpis.elfonavidenyo.domain.model.ElfAdventure
import com.calleserpis.elfonavidenyo.domain.repository.ElfRepository
import kotlinx.coroutines.flow.Flow

class GetElfAdventuresUseCase(private val repository: ElfRepository)  {
    operator fun invoke(): Flow<List<ElfAdventure>> {
        return repository.getElfAdventures()
    }
}



