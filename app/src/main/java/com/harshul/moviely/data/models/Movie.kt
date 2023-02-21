package com.harshul.moviely.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "table_movie")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val summary: String,
    val thumbnail: String,
    val banner: String,
    val release_date: String,
    val duration: String,
    val rating: Float,
    var user_rating: Float,
    val category: String,
    val category_id: Int,
    val reviews: Int,
    val popularity: Int,
    val trailer: String,
    val cast: List<Actor>?,
    var is_favourite: Boolean
) : Parcelable