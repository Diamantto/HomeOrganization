package com.example.homeapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Flat(
    var number: Int,
    var roomQuantity: Int,
    var personQuantity: Int,
    var flatDescription: String,

    @PrimaryKey(autoGenerate = true)
    var flatId: Int = 0

)