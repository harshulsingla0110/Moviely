package com.harshul.moviely.data.di

import android.content.Context
import androidx.room.Room
import com.harshul.moviely.data.db.MoviesDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideFakerDB(@ApplicationContext context: Context): MoviesDB {
        return Room.databaseBuilder(context, MoviesDB::class.java, "MoviesDB").build()
    }

}