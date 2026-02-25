package com.calleserpis.elfontheshelf.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed class Screens{



    @Serializable
    data object Home: Screens() {

         val icon: ImageVector = Icons.Default.Home
    }

    @Serializable
    data object Favourites: Screens() {

         val icon: ImageVector = Icons.Default.Star
    }
    @Serializable
    data class Detail(val adventureId: Int): Screens() {


    }
}
