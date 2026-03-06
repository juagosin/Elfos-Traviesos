package com.calleserpis.elfonavidenyo.ui.screens.detail

sealed class DetailEvent {
    data class OnFavoriteClick(val adventureId: Int) : DetailEvent()
    data class OnAdventureLoad(val adventureId: Int) : DetailEvent()
    data class LoadAdventure(val adventureId: Int) : DetailEvent()
}



