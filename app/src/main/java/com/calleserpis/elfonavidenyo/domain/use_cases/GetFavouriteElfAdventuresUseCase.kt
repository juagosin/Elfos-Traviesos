package com.calleserpis.elfonavidenyo.domain.use_cases

import com.calleserpis.elfonavidenyo.domain.model.ElfAdventure
import com.calleserpis.elfonavidenyo.domain.repository.ElfRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteElfAdventuresUseCase(
    private val repository: ElfRepository
) {
    operator fun invoke(): Flow<List<ElfAdventure>> {
        return repository.getFavouritesElfAdventures()
    }
}

