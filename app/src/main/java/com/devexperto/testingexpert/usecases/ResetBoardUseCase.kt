package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.BoardRepository
import javax.inject.Inject

class ResetBoardUseCase @Inject constructor(
    private val boardRepository: BoardRepository
) {
    suspend operator fun invoke() {
        boardRepository.reset()
    }
}