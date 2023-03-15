package com.example.projeczara.data.database

import androidx.room.TypeConverter
import com.example.projeczara.data.domain.Location
import com.example.projeczara.data.domain.Origin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromOrigin(value: String): Origin {
        val listType = object : TypeToken<Origin>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: Origin): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromLocation(value: String): Location {
        val listType = object : TypeToken<Location>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: Location): String {
        val gson = Gson()
        return gson.toJson(list)
    }


    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}