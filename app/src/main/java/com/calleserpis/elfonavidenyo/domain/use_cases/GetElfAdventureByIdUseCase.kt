package com.calleserpis.elfonavidenyo.domain.use_cases

import com.calleserpis.elfonavidenyo.domain.repository.ElfRepository

class GetElfAdventureByIdUseCase( private val repository: ElfRepository) {
    operator  fun invoke(id: Int?) = repository.getElfAdventureById(id)
}