package com.example.projeczara.data.database.character

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table")
    fun getCharacters(): Flow<List<Character>>

    @Query("SELECT * FROM character_table WHERE id=:id")
    fun findById(id: Int): Flow<Character>

    @Query("SELECT COUNT(id) From character_table")
    fun characterCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacter(characters: List<Character>)

    @Update
    fun updateCharacter(title: Character)
}