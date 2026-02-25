package com.calleserpis.elfontheshelf.ui.screens.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.calleserpis.elfontheshelf.R
import com.calleserpis.elfontheshelf.ui.screens.home.ElfTagRow
import com.calleserpis.elfontheshelf.ui.screens.home.FavoriteButton

@Composable
fun DetailScreen(
    adventureId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    Log.e("DetailScreen", "adventureId: $adventureId")
    LaunchedEffect(adventureId) {

        viewModel.onEvent(DetailEvent.LoadAdventure(adventureId))

    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    val adventure = state.adventure

    Column(

        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.surfaceContainer
            )
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())

    ) {
        Spacer(modifier = Modifier.padding(4.dp))
        if (adventure != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)

            ) {
                AsyncImage(
                    model = "https://i.pinimg.com/236x/6c/f5/46/6cf546f5f8b6b464e8d45d05ad0dfbc9.jpg",//adventure.imageRes,
                    contentDescription = "Titulo",//adventure.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    placeholder = painterResource(R.drawable.default_elf),
                    error = painterResource(R.drawable.default_elf),
                    onError = {
                        Log.e("CoilError", "Error cargando imagen:")
                    }
                )
                FavoriteButton(
                    isFavorite = adventure.isFavorite,
                    onClick = {
                        viewModel.onEvent(DetailEvent.OnFavoriteClick(adventure.id))
                    },

                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                )
            }
            Text(
                text = state.adventure!!.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold
            )
            HorizontalDivider(color = Color(0xFFEEEEEE))

            ElfTagRow(tags = state.adventure!!.tags)

            Text(
                text = state.adventure!!.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray,
                lineHeight = 22.sp
            )
        }
    }
}