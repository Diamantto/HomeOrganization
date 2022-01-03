package com.example.homeapp.di

import android.content.Context
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.data.db.OrganizerDatabase
import com.example.homeapp.data.db.daos.OrganizerDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule (
    private val mAppContext: Context
) {

    @Provides
    fun provideAppContext(): Context {
        return mAppContext
    }

    @Provides
    @Singleton
    fun provideAppDatabase(appContext: Context): OrganizerDatabase {
        return OrganizerDatabase.getDatabase(appContext)
    }

    @Provides
    @Singleton
    fun provideOrganizerDao(organizerDatabase: OrganizerDatabase): OrganizerDao {
        return organizerDatabase.organizerDao()
    }

    @Provides
    @Singleton
    fun provideAppDataSource(organizerDao: OrganizerDao): AppDataSource {
        return AppDataSource(organizerDao)
    }
}