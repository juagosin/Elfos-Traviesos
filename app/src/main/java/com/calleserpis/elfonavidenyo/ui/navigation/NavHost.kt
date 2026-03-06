package com.calleserpis.elfonavidenyo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.calleserpis.elfonavidenyo.ui.screens.detail.DetailScreen
import com.calleserpis.elfonavidenyo.ui.screens.favourites.FavouritesScreen
import com.calleserpis.elfonavidenyo.ui.screens.home.HomeScreen
import com.calleserpis.elfonavidenyo.ui.screens.ideas.IdeasScreen

@Composable
fun ElfNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home,
        modifier = modifier
    ) {
        composable<Screens.Home> {
            HomeScreen(
                onCardClick = { adventureId ->
                    navController.navigate(Screens.Detail(adventureId = adventureId))
                }
            )
        }
        composable<Screens.Favourites> {
            FavouritesScreen(
                onCardClick = { adventureId ->
                    navController.navigate(Screens.Detail(adventureId = adventureId))
                }
            )
        }
        composable<Screens.Ideas> {
            IdeasScreen()
        }
        composable<Screens.Detail> { backStackEntry ->
            val detail: Screens.Detail = backStackEntry.toRoute()
            DetailScreen(
                adventureId = detail.adventureId
            )
        }

    }
}