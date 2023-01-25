package com.devexperto.testingexpert.composeui.scoreboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devexperto.testingexpert.domain.Score
import com.devexperto.testingexpert.ui.formatToString
import com.devexperto.testingexpert.ui.scoreboard.ScoreboardViewModel

@Composable
fun Scoreboard(vm: ScoreboardViewModel = hiltViewModel()) {
    val scores by vm.scores.collectAsState(initial = emptyList())
    Scoreboard(scores)
}

@Composable
fun Scoreboard(scores: List<Score>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(scores) {
            ScoreboardItem(it)
        }
    }
}

@Composable
fun ScoreboardItem(score: Score) {
    Card {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = score.winner.toString(),
                style = MaterialTheme.typography.h5
            )
            Text(
                text = "Number of moves: ${score.numberOfMoves}",
                modifier = Modifier.weight(1f)
            )
            Text(text = score.date.formatToString())
        }
    }
}
