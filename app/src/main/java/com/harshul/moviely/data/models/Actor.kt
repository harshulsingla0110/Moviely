package com.harshul.moviely.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Actor(
    val name: String,
    val photo: String,
    val dob: String
) : Parcelable
