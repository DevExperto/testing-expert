package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.BoardRepository
import javax.inject.Inject

class MakeBoardMoveUseCase @Inject constructor(
    private val boardRepository: BoardRepository
) {
    suspend operator fun invoke(row: Int, column: Int) {
        boardRepository.move(row, column)
    }
}