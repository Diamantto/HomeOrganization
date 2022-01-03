package com.example.homeapp.data.db.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.homeapp.data.db.entities.Billing
import com.example.homeapp.data.db.entities.Flat

data class FlatWithBillings(
    @Embedded
    val flat: Flat,

    @Relation(parentColumn = "flatId", entityColumn = "flatNumber")
    val billing: List<Billing>
)