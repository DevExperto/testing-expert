package com.devexperto.testingexpert.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface GamesService {

    @GET("games")
    suspend fun getGames(
        @Query("key") key: String,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 50
    ): GamesResponse
}