package com.example.homeapp

import android.app.Application
import com.example.homeapp.di.DaggerAppComponent
import com.example.homeapp.di.DatabaseModule

class MyApplication: Application() {

    lateinit var appComponent: DaggerAppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .databaseModule(DatabaseModule(applicationContext))
            .build() as DaggerAppComponent
    }

}