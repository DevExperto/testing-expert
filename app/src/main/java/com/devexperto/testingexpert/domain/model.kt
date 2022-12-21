package com.devexperto.testingexpert.domain

sealed interface CellValue
sealed interface Player
sealed interface Winner

data class TicTacToe(
    val board: List<List<CellValue>> = List(3) { List(3) { Empty } },
    val currentPlayer: Player = X
)

object X : CellValue, Player, Winner {
    override fun toString(): String = "X"
}

object O : CellValue, Player, Winner {
    override fun toString(): String = "O"
}

object Empty : CellValue {
    override fun toString(): String = " "
}

object Draw : Winner