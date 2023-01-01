package com.devexperto.testingexpert.domain

import java.util.*

data class VideoGame(
    val id: Int,
    val name: String,
    val rating: Double,
    val imageUrl: String,
    val releaseDate: Date,
)
