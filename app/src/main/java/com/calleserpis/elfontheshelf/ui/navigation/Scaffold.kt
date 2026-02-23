package com.calleserpis.elfontheshelf.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun ElfScaffold(modifier: Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: String? = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            //TODO ElfTopAppBar(navController)
        },
        bottomBar = {
            //TODO ElfBottomBar(navController)
        },
        floatingActionButton = {
            //TODO ElfFloatingActionButton(navController)
        }
    ) { innerPadding ->
        ElfNavHost(modifier.padding(innerPadding), navController)

    }

}