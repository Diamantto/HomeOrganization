package com.example.homeapp.data.db.daos

import androidx.room.*
import com.example.homeapp.data.db.entities.Billing
import com.example.homeapp.data.db.entities.Flat
import com.example.homeapp.data.db.entities.Person
import com.example.homeapp.data.db.entities.relations.FlatWithBillings
import com.example.homeapp.data.db.entities.relations.FlatWithPersons

@Dao
interface OrganizerDao {

    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBilling(billing: Billing)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlat(flat: Flat)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: Person)

    //Get
    @Query("SELECT * FROM flat WHERE flatId = :id")
    fun getFlat(id: Int): Flat?

    @Query("SELECT * FROM flat")
    fun getFlats(): List<Flat>

    @Query("SELECT * FROM billing WHERE billingId = :id")
    fun getBilling(id: Int): Billing?

    @Query("SELECT * FROM billing WHERE flatNumber = :id")
    fun getBillings(id: Int): List<Billing>

    @Query("SELECT * FROM billing")
    fun getAllBillings(): List<Billing>

    @Query("SELECT * FROM person WHERE personId = :id")
    fun getPerson(id: Int): Person?

    @Query("SELECT * FROM person WHERE flatNumber = :id")
    fun getPersons(id: Int): List<Person>

    @Query("SELECT * FROM person")
    fun getAllPersons(): List<Person>

    //Relations
    @Transaction
    @Query("SELECT * FROM flat")
    fun getFlatsWithPersons(): List<FlatWithPersons>

    @Transaction
    @Query("SELECT * FROM flat WHERE flatId = :id")
    fun getFlatWithPersons(id: Int): FlatWithPersons?

    @Transaction
    @Query("SELECT * FROM flat")
    fun getFlatsWithBillings(): List<FlatWithBillings>

    @Transaction
    @Query("SELECT * FROM flat WHERE flatId = :id")
    fun getFlatWithBillings(id: Int): FlatWithBillings?

    //Delete
    @Query("DELETE FROM person WHERE personId = :id")
    suspend fun deletePerson(id: Int)

    @Query("DELETE FROM billing WHERE billingId = :id")
    suspend fun deleteBilling(id: Int)

    @Query("DELETE FROM flat WHERE flatId = :id")
    suspend fun deleteFlat(id: Int)

}