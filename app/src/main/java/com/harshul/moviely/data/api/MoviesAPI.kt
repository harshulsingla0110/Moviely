package com.harshul.moviely.data.api

import com.harshul.moviely.data.models.Movie
import retrofit2.Response
import retrofit2.http.GET

interface MoviesAPI {

    @GET("movies")
    suspend fun getMovies(): Response<List<Movie>>
}