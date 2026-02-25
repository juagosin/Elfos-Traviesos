package com.calleserpis.elfontheshelf.ui.screens.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.calleserpis.elfontheshelf.ui.screens.home.ElfAdventureCard

@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel = hiltViewModel(),
    onCardClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()

    if (state.elfAdventures.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No hay elementos favoritos todavía",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.surfaceContainer
                )
        ) {
            items(
                count = state.elfAdventures.size
            ) { index ->
                val adventure = state.elfAdventures.getOrNull(index) ?: return@items

                ElfAdventureCard(
                    adventure = adventure,
                    onFavoriteClick = {
                        viewModel.onFavoriteClick(adventure.id)
                    },
                    onCardClick = {
                        onCardClick(adventure.id)
                    }
                )
            }
        }
    }
}

