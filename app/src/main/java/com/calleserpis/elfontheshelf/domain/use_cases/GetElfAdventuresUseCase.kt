package com.calleserpis.elfontheshelf.domain.use_cases

import com.calleserpis.elfontheshelf.domain.model.ElfAdventure
import com.calleserpis.elfontheshelf.domain.repository.ElfRepository
import kotlinx.coroutines.flow.Flow

class GetElfAdventuresUseCase(private val repository: ElfRepository)  {
    operator fun invoke(): Flow<List<ElfAdventure>> {
        return repository.getElfAdventures()
    }
}



