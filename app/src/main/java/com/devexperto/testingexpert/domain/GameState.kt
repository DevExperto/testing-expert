package com.devexperto.testingexpert.domain

sealed interface GameState {
    object NotStarted : GameState
    object InProgress : GameState
    data class Finished(val winner: Winner) : GameState
}