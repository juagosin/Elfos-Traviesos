package com.calleserpis.elfonavidenyo.domain.use_cases

import com.calleserpis.elfonavidenyo.domain.repository.ElfRepository

class ToggleFavoriteUseCase(
    private val repository: ElfRepository
) {
    suspend operator fun invoke(id: Int) {
         repository.toggleFavorite(id)
    }
}

