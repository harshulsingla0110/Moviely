package com.harshul.moviely.data.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.harshul.moviely.data.models.Actor
import java.lang.reflect.Type
import java.util.*

@TypeConverters
class MovieTypeConvertors {

    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<Actor?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Actor?>?>() {}.type
        return gson.fromJson<List<Actor?>>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Actor?>?): String? {
        return gson.toJson(someObjects)
    }

}