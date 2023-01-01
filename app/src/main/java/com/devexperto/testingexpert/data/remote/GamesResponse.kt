package com.devexperto.testingexpert.data.remote

import com.google.gson.annotations.SerializedName

data class GamesResponse(
    val results: List<Result>
)

data class Result(
    val id: Int,
    val name: String,
    val released: String,
    val rating: Double,
    @SerializedName("background_image")
    val backgroundImage: String,
)