package com.devexperto.testingexpert.ui.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devexperto.testingexpert.data.BoardRepository
import com.devexperto.testingexpert.data.ScoreboardRepository
import com.devexperto.testingexpert.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val boardRepository: BoardRepository,
    private val scoreboardRepository: ScoreboardRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun startGame() {
        viewModelScope.launch {
            boardRepository.board.collect { board ->
                _state.value = UiState(
                    ticTacToe = board,
                    gameState = when (val winner = board.findWinner()) {
                        null -> GameState.InProgress
                        else -> {
                            scoreboardRepository.addScore(
                                Score(
                                    winner,
                                    board.numberOfMoves(),
                                    Date()
                                )
                            )
                            GameState.Finished(winner)
                        }
                    }
                )
            }
        }
    }

    fun move(row: Int, column: Int) {
        viewModelScope.launch {
            boardRepository.move(row, column)
        }
    }

    fun resetGame() {
        viewModelScope.launch {
            boardRepository.reset()
            startGame()
        }
    }

    data class UiState(
        val ticTacToe: TicTacToe = TicTacToe(),
        val gameState: GameState = GameState.NotStarted
    )
}