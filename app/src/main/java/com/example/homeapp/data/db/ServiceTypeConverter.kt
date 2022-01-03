package com.example.homeapp.data.db

import androidx.room.TypeConverter
import com.example.homeapp.data.db.entities.Month
import com.example.homeapp.data.db.entities.Service
import com.google.gson.Gson

class ServiceTypeConverter {
    @TypeConverter
    fun toService(serviceString: String) =
        Gson().fromJson(serviceString, Service::class.java)

    @TypeConverter
    fun fromService(service: Service) =
        Gson().toJson(service)
}