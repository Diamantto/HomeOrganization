package com.example.homeapp.data.db.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.homeapp.data.db.entities.Flat
import com.example.homeapp.data.db.entities.Person

data class FlatWithPersons(
    @Embedded
    val flat: Flat,

    @Relation(parentColumn = "flatId", entityColumn = "flatNumber")
    val persons: List<Person>
)