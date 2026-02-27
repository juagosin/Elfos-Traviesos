package com.calleserpis.elfontheshelf.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.calleserpis.elfontheshelf.R

@Composable
fun ElfBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar(containerColor = Color.Transparent){
        NavigationBarItem(
            label = {
                Text(text = stringResource(R.string.bottom_bar_home))
            },
            selected = currentRoute?.contains("Home") == true,
            onClick = {
                navController.navigate(Screens.Home) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            },
            icon = {
                Icon( Screens.Home.icon, contentDescription = "")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Red,
                selectedTextColor = Color.Red,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            label = {
                Text(text = stringResource(R.string.bottom_bar_favourites))
            },
            selected = currentRoute?.contains("Favourites") == true,
            onClick = {
                navController.navigate(Screens.Favourites){
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }

                    launchSingleTop = true

                    restoreState = true
                }
            },
            icon = {
                Icon( Screens.Favourites.icon, contentDescription = "")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Red,
                selectedTextColor = Color.Red,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            label = {
                Text(text = stringResource(R.string.bottom_bar_ideas))
            },
            selected = currentRoute?.contains("Ideas") == true,
            onClick = {
                navController.navigate(Screens.Ideas){
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }

                    launchSingleTop = true

                    restoreState = true
                }
            },
            icon = {
                Icon( Screens.Ideas.icon, contentDescription = "")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Red,
                selectedTextColor = Color.Red,
                indicatorColor = Color.Transparent
            )
        )
    }
}