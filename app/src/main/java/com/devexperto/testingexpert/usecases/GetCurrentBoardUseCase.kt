package com.devexperto.testingexpert.usecases

import com.devexperto.testingexpert.data.BoardRepository
import javax.inject.Inject

class GetCurrentBoardUseCase @Inject constructor(private val boardRepository: BoardRepository) {
    operator fun invoke() = boardRepository.board
}