package com.devexperto.testingexpert.ui.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devexperto.testingexpert.domain.GameState
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.findWinner
import com.devexperto.testingexpert.usecases.AddScoreUseCase
import com.devexperto.testingexpert.usecases.GetCurrentBoardUseCase
import com.devexperto.testingexpert.usecases.MakeBoardMoveUseCase
import com.devexperto.testingexpert.usecases.ResetBoardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val makeBoardMoveUseCase: MakeBoardMoveUseCase,
    private val getCurrentBoardUseCase: GetCurrentBoardUseCase,
    private val addScoreUseCase: AddScoreUseCase,
    private val resetBoardUseCase: ResetBoardUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun startGame() {
        viewModelScope.launch {
            getCurrentBoardUseCase().collect { board ->
                _state.value = UiState(
                    ticTacToe = board,
                    gameState = when (val winner = board.findWinner()) {
                        null -> GameState.InProgress
                        else -> {
                            addScoreUseCase(board)
                            GameState.Finished(winner)
                        }
                    }
                )
            }
        }
    }

    fun move(row: Int, column: Int) {
        viewModelScope.launch {
            makeBoardMoveUseCase(row, column)
        }
    }

    fun resetGame() {
        viewModelScope.launch {
            resetBoardUseCase()
            startGame()
        }
    }

    data class UiState(
        val ticTacToe: TicTacToe = TicTacToe(),
        val gameState: GameState = GameState.NotStarted
    )
}