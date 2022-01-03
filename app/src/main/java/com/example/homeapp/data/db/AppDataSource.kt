package com.example.homeapp.data.db

import com.example.homeapp.data.db.daos.OrganizerDao
import com.example.homeapp.data.db.entities.Billing
import com.example.homeapp.data.db.entities.Flat
import com.example.homeapp.data.db.entities.Person
import com.example.homeapp.data.db.entities.relations.FlatWithBillings
import com.example.homeapp.data.db.entities.relations.FlatWithPersons

class AppDataSource(
    private val organizerDao: OrganizerDao
) {

    //Get data
    fun getFlat(id: Int): Flat? = organizerDao.getFlat(id)

    fun getFlats(): List<Flat> = organizerDao.getFlats()

    fun getBilling(id: Int): Billing? = organizerDao.getBilling(id)

    fun getAllBillings(): List<Billing> = organizerDao.getAllBillings()

    fun getPerson(id: Int): Person? = organizerDao.getPerson(id)


    fun getAllPersons(): List<Person> = organizerDao.getAllPersons()

    //Get relations

    fun getFlatWithPersons(id: Int): FlatWithPersons? {
        return organizerDao.getFlatWithPersons(id)
    }

    fun getFlatWithBillings(id: Int): FlatWithBillings? {
        return organizerDao.getFlatWithBillings(id)
    }

    //Insert data
    suspend fun insertFlat(flat: Flat): Boolean {
        if (flat.roomQuantity < 0) {
            return false
        }
        organizerDao.insertFlat(flat)
        return true
    }

    suspend fun insertBilling(billing: Billing): Boolean {
        if (billing.flatNumber < 0) {
            return false
        }
        organizerDao.insertBilling(billing)
        return true
    }

    suspend fun insertPerson(person: Person): Boolean {
        if (person.flatNumber < 0) {
            return false
        }
        organizerDao.insertPerson(person)
        return true
    }

    //Delete data
    suspend fun deletePerson(id: Int): Boolean {
        if (id < 0) {
            return false
        }
        organizerDao.deletePerson(id)
        return true
    }

    suspend fun deleteBilling(id: Int): Boolean {
        if (id < 0) {
            return false
        }
        organizerDao.deleteBilling(id)
        return true
    }

    suspend fun deleteFlat(id: Int): Boolean {
        if (id < 0) {
            return false
        }
        organizerDao.deleteFlat(id)
        return true
    }
}