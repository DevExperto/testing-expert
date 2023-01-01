package com.devexperto.testingexpert.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MoveEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val row: Int,
    val column: Int
)