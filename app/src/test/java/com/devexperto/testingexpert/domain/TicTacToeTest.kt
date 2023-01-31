package com.devexperto.testingexpert.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class TicTacToeTest {

    @Test
    fun `at the beginning of the game, the board is empty`() {
        val ticTacToe = TicTacToe()

        assertTrue(ticTacToe.board.flatten().all { it == Empty })
    }

    @Test
    fun `the first player is X`() {
        val ticTacToe = TicTacToe()
            .move(0, 0)

        assertEquals(X, ticTacToe.board[0][0])
    }

    @Test
    fun `the second player is O`() {
        val ticTacToe = TicTacToe()
            .move(0, 0)
            .move(0, 1)

        assertEquals(X, ticTacToe.board[0][0])
        assertEquals(O, ticTacToe.board[0][1])
    }

    @Test
    fun `an occupied cell cannot be played`() {
        val ticTacToe = TicTacToe()
            .move(0, 0)
            .move(0, 0)

        assertEquals(X, ticTacToe.board[0][0])
    }

    @Test
    fun `the game ends when all cells in a row are taken by a player`() {
        val ticTacToe = TicTacToe()
            .move(0, 0)
            .move(1, 0)
            .move(0, 1)
            .move(1, 1)
            .move(0, 2)

        assertEquals(X, ticTacToe.findWinner())
    }

    @Test
    fun `the game ends when all cells in a column are taken by a player`() {
        val ticTacToe = TicTacToe()
            .move(0, 0)
            .move(0, 1)
            .move(1, 0)
            .move(1, 1)
            .move(2, 0)

        assertEquals(X, ticTacToe.findWinner())
    }

    @Test
    fun `the game ends when all cells in a diagonal are taken by a player`() {
        val ticTacToe = TicTacToe()
            .move(0, 0)
            .move(0, 1)
            .move(1, 1)
            .move(1, 0)
            .move(2, 2)

        assertEquals(X, ticTacToe.findWinner())
    }

    @Test
    fun `the game ends when all cells are taken`() {
        val ticTacToe = TicTacToe()
            .move(0, 0)
            .move(0, 1)
            .move(0, 2)
            .move(1, 2)
            .move(1, 0)
            .move(2, 0)
            .move(1, 1)
            .move(2, 2)
            .move(2, 1)

        assertEquals(Draw, ticTacToe.findWinner())
    }

    @Test
    fun `when asked for a winner and there is no winner, it returns null`() {
        val ticTacToe = TicTacToe()
            .move(0, 0)

        assertEquals(null, ticTacToe.findWinner())
    }

    @Test
    fun `when O wins, the game ends`() {
        val ticTacToe = TicTacToe()
            .move(0, 0)
            .move(0, 1)
            .move(1, 2)
            .move(1, 1)
            .move(0, 2)
            .move(2, 1)

        assertEquals(O, ticTacToe.findWinner())
    }
}