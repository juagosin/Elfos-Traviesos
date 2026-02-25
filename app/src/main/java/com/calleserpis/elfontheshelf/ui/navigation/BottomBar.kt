package com.calleserpis.elfontheshelf.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun ElfBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar(containerColor = Color.Transparent){
        NavigationBarItem(
            label = {
                Text(text = "Home")
            },
            selected = currentRoute?.contains("Home") == true,
            onClick = {
                navController.navigate(Screens.Home.route){
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }

                    launchSingleTop = true

                    restoreState = true
                }
            },
            icon = {
                Icon( Screens.Home.icon, contentDescription = "")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Red,
                selectedTextColor = Color.Red,
                indicatorColor = Color.Transparent
                //indicatorColor = MaterialTheme.colorScheme.primary
            )
        )
        NavigationBarItem(
            label = {
                Text(text = "Favourites")
            },
            selected = currentRoute?.contains("Favourites") == true,
            onClick = { /*TODO*/ },
            icon = {
                Icon( Screens.Favourites.icon, contentDescription = "")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Red,
                selectedTextColor = Color.Red,
                indicatorColor = Color.Transparent
                //indicatorColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}