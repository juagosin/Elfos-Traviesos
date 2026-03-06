package com.calleserpis.elfonavidenyo.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.calleserpis.elfonavidenyo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElfTopAppBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: String? = navBackStackEntry?.destination?.route
    val isDetailRoute = currentRoute?.contains("Screens.Detail") == true
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.top_app_bar_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,

                //modifier = Modifier.padding(12.dp)
            )
        },
        colors = topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            Row {
                if (isDetailRoute) {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.celebration_icon),
                        contentDescription = null
                    )
                }
            }
        }
    )
}