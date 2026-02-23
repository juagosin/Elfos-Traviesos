package com.calleserpis.elfontheshelf.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed class Screens{
    abstract val route: String
    abstract val icon: ImageVector

    @Serializable
    data object Home: Screens() {
        override val route: String = "home"
        override val icon: ImageVector = Icons.Default.Home
    }

    @Serializable
    data object Favourites: Screens() {
        override val route: String = "favourites"
        override val icon: ImageVector = Icons.Default.Star
    }
    @Serializable
    data object Detail: Screens() {
        override val route: String = "detail"
        override val icon: ImageVector = Icons.Default.Star
    }
}
