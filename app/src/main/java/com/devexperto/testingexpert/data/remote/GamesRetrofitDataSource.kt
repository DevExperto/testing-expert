package com.devexperto.testingexpert.data.remote

import com.devexperto.architectcoders.di.ApiKey
import com.devexperto.testingexpert.data.datasource.GamesRemoteDataSource
import com.devexperto.testingexpert.domain.VideoGame
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GamesRetrofitDataSource @Inject constructor(
    private val gamesService: GamesService,
    @ApiKey private val apiKey: String
) : GamesRemoteDataSource {
    override suspend fun getGames(): List<VideoGame> {
        return gamesService.getGames(apiKey).results.map { it.toDomainModel() }
    }
}

private fun Result.toDomainModel(): VideoGame =
    VideoGame(
        id = id,
        name = name,
        rating = rating,
        imageUrl = backgroundImage,
        releaseDate = released.toDate()
    )

private fun String.toDate(): Date {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return format.parse(this) as Date
}
