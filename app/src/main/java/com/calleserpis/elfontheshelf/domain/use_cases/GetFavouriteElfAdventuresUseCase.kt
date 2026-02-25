package com.calleserpis.elfontheshelf.domain.use_cases

import com.calleserpis.elfontheshelf.domain.model.ElfAdventure
import com.calleserpis.elfontheshelf.domain.repository.ElfRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteElfAdventuresUseCase(
    private val repository: ElfRepository
) {
    operator fun invoke(): Flow<List<ElfAdventure>> {
        return repository.getFavouritesElfAdventures()
    }
}

