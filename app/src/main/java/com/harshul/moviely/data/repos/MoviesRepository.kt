package com.harshul.moviely.data.repos

import com.harshul.moviely.data.api.MoviesAPI
import com.harshul.moviely.data.db.MoviesDB
import com.harshul.moviely.data.models.Movie
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesAPI: MoviesAPI,
    private val moviesDB: MoviesDB
) {

    suspend fun hitAPI() {
        val result = moviesAPI.getMovies()
        if (result.isSuccessful && result.body() != null) {
            moviesDB.getMoviesDAO().addMovies(result.body()!!)
        }
    }

    fun getAllMovies() = moviesDB.getMoviesDAO().getAllMovies()

    fun getFavMovies() = moviesDB.getMoviesDAO().getFavMovies()

    suspend fun updateMovie(movie: Movie) = moviesDB.getMoviesDAO().updateMovie(movie)

    fun getTrendingMovies() = moviesDB.getMoviesDAO().getTrendingMovies()

    fun getGenreMovies(genre: String) = moviesDB.getMoviesDAO().getGenreMovies(genre)

}