package com.devexperto.testingexpert.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devexperto.testingexpert.domain.Winner
import java.util.*

@Entity
data class ScoreEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val winner: Winner,
    val numberOfMoves: Int,
    val date: Date
)