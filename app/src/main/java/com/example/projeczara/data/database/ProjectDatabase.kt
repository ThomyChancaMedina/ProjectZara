package com.example.projeczara.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.projeczara.data.database.character.Character
import com.example.projeczara.data.database.character.CharacterDao

@Database(
    entities = [Character::class],
    version = 1, exportSchema = true)
@TypeConverters(Converter::class)
abstract class ProjectDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}