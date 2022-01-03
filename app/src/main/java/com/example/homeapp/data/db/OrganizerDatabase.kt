package com.example.homeapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.homeapp.data.db.daos.OrganizerDao
import com.example.homeapp.data.db.entities.Billing
import com.example.homeapp.data.db.entities.Flat
import com.example.homeapp.data.db.entities.Person

@Database(
    entities = [Flat::class, Person::class, Billing::class],
    version = 1
)
@TypeConverters(ServiceTypeConverter::class, MonthTypeConverter::class)
abstract class OrganizerDatabase : RoomDatabase() {

    abstract fun organizerDao(): OrganizerDao

    companion object {
        @Volatile
        private var instance: OrganizerDatabase? = null

        fun getDatabase(context: Context): OrganizerDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, OrganizerDatabase::class.java, "OrganizerDatabase")
                .fallbackToDestructiveMigration()
                .build()
    }
}