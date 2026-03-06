package com.calleserpis.elfonavidenyo.domain.use_cases

class ElfAdventuresUseCases(
    val getElfAdventures: GetElfAdventuresUseCase,
    val getElfAdventureById: GetElfAdventureByIdUseCase,
    val toggleFavorite: ToggleFavoriteUseCase,
    val getFavouriteElfAdventures: GetFavouriteElfAdventuresUseCase
)