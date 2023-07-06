package com.example.task_manager

import android.app.Application
import androidx.room.Room
import com.example.task_manager.data.local.db.AppDatabase
import com.google.firebase.FirebaseApp

class App: Application() {

    companion object{
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
         db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "database-name"
        )
             .allowMainThreadQueries()
             .build()
    }

}