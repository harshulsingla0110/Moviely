package com.harshul.moviely.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harshul.moviely.data.models.Movie
import com.harshul.moviely.data.repos.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    suspend fun getMoviesFromAPI() = repository.hitAPI()

    fun getAllMovies() = repository.getAllMovies()

    fun getFavMovies() = repository.getFavMovies()

    fun updateMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMovie(movie)
        }
    }

    fun getTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val c = repository.getTrendingMovies()
        }
    }

    fun getGenreMovies(genre: String) = repository.getGenreMovies(genre)

}