package com.devexperto.testingexpert.domain

fun TicTacToe.move(moveRow: Int, moveColumn: Int): TicTacToe {
    val newBoard = board.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, cell ->
            if (rowIndex == moveRow && columnIndex == moveColumn && cell == Empty) currentPlayer as CellValue else cell
        }
    }
    return TicTacToe(newBoard, nextPlayer())
}

private fun TicTacToe.nextPlayer(): Player = if (currentPlayer == X) O else X

fun TicTacToe.findWinner(): Winner? {
    return when {
        isWinner(X) -> X
        isWinner(O) -> O
        isBoardComplete() -> Draw
        else -> null
    }
}

fun TicTacToe.numberOfMoves(): Int = board.flatten().count { it != Empty }

private fun TicTacToe.isBoardComplete(): Boolean {
    return board.flatten().none { it == Empty }
}

private fun TicTacToe.isWinner(player: CellValue): Boolean {
    return board.any { row -> row.all { it == player } } ||
            board[0][0] == player && board[1][1] == player && board[2][2] == player ||
            board[0][2] == player && board[1][1] == player && board[2][0] == player ||
            board[0][0] == player && board[1][0] == player && board[2][0] == player ||
            board[0][1] == player && board[1][1] == player && board[2][1] == player ||
            board[0][2] == player && board[1][2] == player && board[2][2] == player
}
