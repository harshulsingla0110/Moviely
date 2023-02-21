package com.harshul.moviely.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.harshul.moviely.data.models.Movie


@Dao
interface MoviesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovies(moviesList: List<Movie>)

    @Query("SELECT * FROM table_movie")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM table_movie")
    suspend fun getTrendingMovies(): List<Movie>

    @Update
    suspend fun updateMovie(movie: Movie)

    @Query("SELECT * FROM table_movie WHERE is_favourite = 1 ")
    fun getFavMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM table_movie WHERE category LIKE :genre")
    fun getGenreMovies(genre: String): LiveData<List<Movie>>

}