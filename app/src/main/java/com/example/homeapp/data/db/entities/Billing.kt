package com.example.homeapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Billing(
    var flatNumber: Int,
    var time: Month,
    var sum: Float,
    var serviceType: Service,

    @PrimaryKey(autoGenerate = true)
    var billingId: Int = 0
)
