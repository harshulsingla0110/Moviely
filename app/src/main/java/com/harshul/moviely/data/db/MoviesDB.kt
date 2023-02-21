package com.harshul.moviely.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harshul.moviely.data.models.Movie

@Database(entities = [Movie::class], version = 1)
@TypeConverters(MovieTypeConvertors::class)
abstract class MoviesDB : RoomDatabase() {

    abstract fun getMoviesDAO(): MoviesDAO

}