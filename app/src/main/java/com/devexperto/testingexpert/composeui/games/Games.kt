package com.devexperto.testingexpert.composeui.games

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.devexperto.testingexpert.domain.VideoGame
import com.devexperto.testingexpert.ui.formatToString
import com.devexperto.testingexpert.ui.games.GamesViewModel
import com.devexperto.testingexpert.ui.theme.TestingExpertTheme

@Composable
fun Games(vm: GamesViewModel = hiltViewModel()) {
    val state by vm.state.collectAsState()
    LaunchedEffect(vm) {
        vm.onUiReady()
    }
    Games(state)
}

@Composable
fun Games(state: GamesViewModel.UiState) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }

        if (state.games.isNotEmpty()) {
            GamesList(state.games)
        }
    }
}

@Composable
fun GamesList(games: List<VideoGame>) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(games) {
            GameItem(it)
        }
    }
}

@Composable
fun GameItem(game: VideoGame) {
    TestingExpertTheme(darkTheme = true) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            AsyncImage(
                model = game.imageUrl,
                contentDescription = game.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.3f)
            )
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = game.name,
                    modifier = Modifier.align(Alignment.BottomStart),
                    style = MaterialTheme.typography.h5,
                    maxLines = 1
                )

                Text(
                    text = game.releaseDate.formatToString(),
                    style = MaterialTheme.typography.caption
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.secondary)
                        .size(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = String.format("%.1f", game.rating),
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}
