package com.calleserpis.elfontheshelf.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.calleserpis.elfontheshelf.ui.screens.detail.DetailScreen
import com.calleserpis.elfontheshelf.ui.screens.home.HomeScreen

@Composable
fun ElfNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home,
        modifier = modifier
    ) {
        composable<Screens.Home>{
            HomeScreen(
                onFavoriteClick = { /*TODO*/ },
                onCardClick = {
                    adventureId -> navController.navigate(Screens.Detail(adventureId = adventureId))
                }
            )
        }
        composable<Screens.Favourites>{
            //TODO FavScreen()
        }
        composable<Screens.Detail>{
            DetailScreen()
        }

    }
}