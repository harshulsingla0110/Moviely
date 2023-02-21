package com.harshul.moviely.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object SharedPrefUtility {

    fun saveToSharedPreference(
        context: Context,
        key: String,
        value: Any
    ) {
        val localValue: String = value.toString()
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(Constants.FILE_NAME, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, localValue)
        editor.apply()
    }

    fun getFromSharedPreference(
        context: Context,
        key: String
    ): String? {
        val sharedPreferences = context.getSharedPreferences(Constants.FILE_NAME, MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

}