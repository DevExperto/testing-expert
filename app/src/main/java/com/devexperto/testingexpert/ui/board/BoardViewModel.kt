package com.devexperto.testingexpert.ui.board

import androidx.lifecycle.ViewModel
import com.devexperto.testingexpert.domain.GameState
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.findWinner
import com.devexperto.testingexpert.domain.move
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BoardViewModel : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun startGame() {
        _state.value = UiState(
            ticTacToe = TicTacToe(),
            gameState = GameState.InProgress
        )
    }

    fun move(row: Int, column: Int) {
        val newTicTacToe = _state.value.ticTacToe.move(row, column)
        _state.value = UiState(
            ticTacToe = newTicTacToe,
            gameState = when (val winner = newTicTacToe.findWinner()) {
                null -> GameState.InProgress
                else -> GameState.Finished(winner)
            }
        )
    }

    data class UiState(
        val ticTacToe: TicTacToe = TicTacToe(),
        val gameState: GameState = GameState.NotStarted
    )
}