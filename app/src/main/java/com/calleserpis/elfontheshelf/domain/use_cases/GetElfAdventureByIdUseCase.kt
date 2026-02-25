package com.calleserpis.elfontheshelf.domain.use_cases

import com.calleserpis.elfontheshelf.domain.repository.ElfRepository

class GetElfAdventureByIdUseCase( private val repository: ElfRepository) {
    operator  fun invoke(id: Int?) = repository.getElfAdventureById(id)
}