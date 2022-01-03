package com.example.homeapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    var surname: String,
    var name: String,
    var flatNumber: Int,
    var phoneNumber: String,

    @PrimaryKey(autoGenerate = true)
    var personId: Int = 0
)
