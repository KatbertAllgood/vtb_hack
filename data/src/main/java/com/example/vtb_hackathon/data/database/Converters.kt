package com.example.vtb_hackathon.data.database

import androidx.room.TypeConverter
import com.example.vtb_hackathon.data.entity.DepartmentData
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJsonString(value: List<DepartmentData>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) =
        Gson().fromJson(value, Array<DepartmentData>::class.java).toList()
}