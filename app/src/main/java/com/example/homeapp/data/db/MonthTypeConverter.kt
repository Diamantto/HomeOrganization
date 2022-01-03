package com.example.homeapp.data.db

import androidx.room.TypeConverter
import com.example.homeapp.data.db.entities.Month
import com.google.gson.Gson

class MonthTypeConverter {
    @TypeConverter
    fun toMonth(monthString: String) =
        Gson().fromJson(monthString, Month::class.java)

    @TypeConverter
    fun fromMonth(month: Month) =
        Gson().toJson(month)
}