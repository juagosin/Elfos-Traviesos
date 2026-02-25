package com.calleserpis.elfontheshelf.ui.screens.detail

sealed class DetailEvent {
    data class OnFavoriteClick(val adventureId: Int) : DetailEvent()
    data class OnAdventureLOad(val adventureId: Int) : DetailEvent()

}