package com.calleserpis.elfontheshelf.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.calleserpis.elfontheshelf.R
import com.calleserpis.elfontheshelf.common.ElfTags
import com.calleserpis.elfontheshelf.domain.model.ElfAdventure
import com.calleserpis.elfontheshelf.domain.model.ElfTag

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onCardClick: (Int) -> Unit,
) {
    val adventures by viewModel.state.collectAsState()

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
            count = adventures.elfAdventures.size


        ) { index ->
            val adventure = adventures.elfAdventures.getOrNull(index) ?: return@items


            ElfAdventureCard(
                adventure = adventure,
                onFavoriteClick = {
                    viewModel.onFavoriteClick(adventure.id)
                },
                onCardClick = {
                    onCardClick(
                        adventure.id
                    )
                }
            )

        }
    }
}


@Composable
fun ElfAdventureCard(
    adventure: ElfAdventure,
    onFavoriteClick: () -> Unit,
    onCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onCardClick(adventure.id) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            // Imagen con botón favorito superpuesto
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = "https://i.pinimg.com/236x/6c/f5/46/6cf546f5f8b6b464e8d45d05ad0dfbc9.jpg",//adventure.imageRes,
                    contentDescription = adventure.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    placeholder = painterResource(R.drawable.default_elf),
                    error = painterResource(R.drawable.default_elf),
                    onError = {
                        Log.e("CoilError", "Error cargando imagen:")
                    }
                )
                FavoriteButton(
                    isFavorite = adventure.isFavorite,
                    onClick = onFavoriteClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                )
            }

            // Título y tags
            Column(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = adventure.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                ElfTagRow(tags = adventure.tags)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ElfTagRow(
    tags: List<ElfTag>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp,Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.fillMaxWidth(),

    ) {
        tags.forEach { tag ->
            ElfTagChip(tag = tag)
        }
    }
}

@Composable
fun ElfTagChip(
    tag: ElfTag,
    modifier: Modifier = Modifier
) {
    val resolvedTag = ElfTags.findByLabel(tag.label)
    val color = resolvedTag?.let { Color(it.colortype) } ?: MaterialTheme.colorScheme.primary
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .background(color.copy(alpha = 0.15f))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = tag.label,
            style = MaterialTheme.typography.labelSmall,
            color = color,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.85f))
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
            tint = if (isFavorite) Color.Red else Color.Gray,
            modifier = Modifier.size(20.dp)
        )
    }
}