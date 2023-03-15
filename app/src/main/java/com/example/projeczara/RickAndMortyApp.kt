package com.example.projeczara

import android.app.Application
import androidx.room.Room
import com.example.projeczara.data.database.ProjectDatabase

class RickAndMortyApp:Application() {
    lateinit var db: ProjectDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            ProjectDatabase::class.java,
            "rickAndMorty"
        ).build()
    }
}