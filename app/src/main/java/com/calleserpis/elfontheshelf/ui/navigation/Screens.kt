package com.calleserpis.elfontheshelf.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed class Screens{
    abstract val route: String


    @Serializable
    data object Home: Screens() {
        override val route: String = "home"
         val icon: ImageVector = Icons.Default.Home
    }

    @Serializable
    data object Favourites: Screens() {
        override val route: String = "favourites"
         val icon: ImageVector = Icons.Default.Star
    }
    @Serializable
    data class Detail(val adventureId: Int): Screens() {
        override val route: String = "detail"

    }
}
