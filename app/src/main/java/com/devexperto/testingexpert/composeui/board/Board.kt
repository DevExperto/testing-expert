package com.devexperto.testingexpert.composeui.board

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.domain.*
import com.devexperto.testingexpert.ui.board.BoardViewModel

const val BOARD_TEST_TAG = "board"

@Composable
fun Board(vm: BoardViewModel = hiltViewModel()) {
    val state by vm.state.collectAsState()

    Board(
        state = state,
        onStartClick = vm::startGame,
        onMove = vm::move,
        onPlayAgainClick = vm::resetGame
    )
}

@Composable
fun Board(
    state: BoardViewModel.UiState,
    onStartClick: () -> Unit,
    onMove: (Int, Int) -> Unit,
    onPlayAgainClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state.gameState) {
            GameState.NotStarted -> NotStarted(onStartClick)
            GameState.InProgress -> InProgress(state.ticTacToe, onMove)
            is GameState.Finished -> Finished(state.gameState.winner, onPlayAgainClick)
        }
    }
}

@Composable
fun NotStarted(onStartClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            style = MaterialTheme.typography.h5
        )
        Button(onClick = onStartClick) {
            Text(text = stringResource(id = R.string.start_game).uppercase())
        }
    }
}

const val CELL_TEST_TAG = "cell"

@Composable
fun InProgress(ticTacToe: TicTacToe, onMove: (Int, Int) -> Unit) {
    Column(
        modifier = Modifier.testTag(BOARD_TEST_TAG)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ticTacToe.board.forEachIndexed { rowIndex, row ->
                Row {
                    row.forEachIndexed { columnIndex, cell ->
                        Cell(
                            cell = cell,
                            onClick = { onMove(rowIndex, columnIndex) },
                            modifier = Modifier.testTag(CELL_TEST_TAG + rowIndex + columnIndex)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.Cell(cell: CellValue, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick, enabled = cell == Empty)
            .border(1.dp, color = MaterialTheme.colors.onBackground)
            .weight(1f)
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = cell.toString(),
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
fun Finished(winner: Winner, onPlayAgainClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = when (winner) {
                X, O -> stringResource(R.string.winner, winner.toString())
                Draw -> stringResource(R.string.draw)
            },
            style = MaterialTheme.typography.h5
        )
        Button(onClick = onPlayAgainClick) {
            Text(text = stringResource(id = R.string.play_again).uppercase())
        }
    }
}
