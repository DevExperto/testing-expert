package com.devexperto.testingexpert

import android.app.Application
import androidx.room.Room
import com.devexperto.testingexpert.data.db.AppDatabase

class App : Application() {

    lateinit var db: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app.db"
        ).build()
    }
}