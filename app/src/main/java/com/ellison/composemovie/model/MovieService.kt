package com.ellison.composemovie.model

import com.ellison.composemovie.bean.*
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("http://omdbapi.com/")
    suspend fun requestSearchByCoroutines(
        @Query("s") keywords: String,
        @Query("apikey") apikey: String
    ): MovieResponse<List<Movie>>

    @GET("http://omdbapi.com/")
    suspend fun requestDetailByCoroutines(
        @Query("i") id: String,
        @Query("apikey") apikey: String
    ): MoviePro
}