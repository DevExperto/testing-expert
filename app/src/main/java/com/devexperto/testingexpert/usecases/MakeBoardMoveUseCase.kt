package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.BoardRepository
import com.devexperto.testingexpert.domain.findWinner
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class MakeBoardMoveUseCase @Inject constructor(
    private val boardRepository: BoardRepository,
    private val addScoreUseCase: AddScoreUseCase
) {
    suspend operator fun invoke(row: Int, column: Int) {
        boardRepository.move(row, column)
        boardRepository.board.firstOrNull()?.let { board ->
            if (board.findWinner() != null) {
                addScoreUseCase(board)
            }
        }
    }
}