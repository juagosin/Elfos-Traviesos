package com.calleserpis.elfontheshelf.domain.use_cases

import com.calleserpis.elfontheshelf.domain.repository.ElfRepository

class ToggleFavoriteUseCase(
    private val repository: ElfRepository
) {
    suspend operator fun invoke(id: Int) {
         repository.toggleFavorite(id)
    }
}

