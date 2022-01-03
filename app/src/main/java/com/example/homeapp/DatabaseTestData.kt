package com.example.homeapp

import com.example.homeapp.data.db.entities.*

val flats = listOf<Flat>(
    Flat(1,4, 3,  "My flat"),
    Flat(2,1, 1,  "Ilya's flat"),
    Flat(3,1, 2,  "Vlad's flat",),
    Flat(4,3, 3,  "Sanya's flat")
)

val persons = listOf<Person>(
    Person("Grishko", "Arthur", 1, "0964477528"),
    Person("Panin", "Ilya", 2, "0123123518"),
    Person("Solodenko", "Vlad", 3, "0561243123"),
    Person("Litvinov", "Alex", 4, "056124123123"),
    Person("Smirnov", "Bogdan", 1, "01235671233"),
)

val billings = listOf<Billing>(
    Billing(1, Month.JANUARY, 500F, Service.OTHERS),
    Billing(1, Month.JANUARY, 300F, Service.WATER)
)