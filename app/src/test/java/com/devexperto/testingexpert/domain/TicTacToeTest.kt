package com.devexperto.testingexpert.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource


class TicTacToeTest {

    @Test
    fun `at the beginning of the game, the board is empty`() {
        val ticTacToe = TicTacToe()

        assertTrue(ticTacToe.board.flatten().all { it == Empty })
    }

    @ParameterizedTest
    @MethodSource
    fun `Given a number of moves, check value in latest cell`(
        moves: List<Pair<Int, Int>>,
        expectedValue: CellValue
    ) {
        val ticTacToe = moves.toTicTacToe()

        moves.last().let { (row, column) ->
            assertEquals(expectedValue, ticTacToe.board[row][column])
        }
    }

    @ParameterizedTest
    @MethodSource
    fun `Given a number of moves, check winner`(
        moves: List<Pair<Int, Int>>,
        winner: Winner?
    ) {
        val ticTacToe = moves.toTicTacToe()

        assertEquals(winner, ticTacToe.findWinner())
    }

    companion object {
        @JvmStatic
        fun `Given a number of moves, check value in latest cell`(): List<Arguments> {
            return listOf(
                // Previous tests
                Arguments.of(listOf(0 to 0), X),
                Arguments.of(listOf(0 to 0, 0 to 1), O),
                Arguments.of(listOf(0 to 0, 0 to 0), X),

                // New tests
                Arguments.of(listOf(0 to 0, 1 to 0, 0 to 1, 1 to 1, 0 to 2), X),
                Arguments.of(listOf(0 to 0, 0 to 1, 1 to 0, 1 to 1, 2 to 0), X),
                Arguments.of(listOf(0 to 0, 0 to 1, 1 to 1, 1 to 0, 2 to 2), X),
                Arguments.of(listOf(0 to 0, 0 to 1, 1 to 2, 1 to 1, 0 to 2, 2 to 1), O),
                Arguments.of(listOf(0 to 0, 0 to 1), O),
                Arguments.of(listOf(0 to 0, 0 to 1, 1 to 2), X),
                Arguments.of(listOf(0 to 0, 0 to 1, 1 to 2, 1 to 1), O),
                Arguments.of(listOf(0 to 0, 0 to 1, 1 to 2, 1 to 1, 2 to 0), X),
                Arguments.of(listOf(0 to 0, 0 to 1, 1 to 2, 1 to 1, 2 to 0, 2 to 1), O),
                Arguments.of(listOf(0 to 0, 0 to 1, 1 to 2, 1 to 1, 2 to 0, 2 to 1, 0 to 2), X),
                Arguments.of(
                    listOf(0 to 0, 0 to 1, 1 to 2, 1 to 1, 2 to 0, 2 to 1, 0 to 2, 1 to 0),
                    O
                ),
            )
        }

        @JvmStatic
        fun `Given a number of moves, check winner`(): List<Arguments> {
            return listOf(
                Arguments.of(listOf(0 to 0, 1 to 0, 0 to 1, 1 to 1, 0 to 2), X),
                Arguments.of(listOf(0 to 0, 0 to 1, 1 to 0, 1 to 1, 2 to 0), X),
                Arguments.of(listOf(0 to 0, 0 to 1, 1 to 1, 1 to 0, 2 to 2), X),
                Arguments.of(
                    listOf(
                        0 to 0,
                        0 to 1,
                        0 to 2,
                        1 to 2,
                        1 to 0,
                        2 to 0,
                        1 to 1,
                        2 to 2,
                        2 to 1
                    ), Draw
                ),
                Arguments.of(listOf(0 to 0, 0 to 1, 1 to 2, 1 to 1, 0 to 2, 2 to 1), O),
                Arguments.of(listOf(0 to 0), null),
            )
        }
    }
}

private fun List<Pair<Int, Int>>.toTicTacToe(): TicTacToe {
    var ticTacToe = TicTacToe()
    this.forEach { (row, column) -> ticTacToe = ticTacToe.move(row, column) }
    return ticTacToe
}
