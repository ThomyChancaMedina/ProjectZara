package com.example.projeczara.data

import com.example.projeczara.data.domain.Character
import com.example.projeczara.datasource.GetCharacterRemoteSource
import com.example.projeczara.datasource.GetCharacterRoomDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val getCharacterRoomDataSource: GetCharacterRoomDataSource,
    private val getCharacterRemoteSource: GetCharacterRemoteSource
) {

    val characters get() = getCharacterRoomDataSource.characters
    suspend fun requestCharacters(): Error? {
        if (getCharacterRoomDataSource.isEmpty()) {
            val listCharacters = getCharacterRemoteSource.requestCharacterFromRemote()
            getCharacterRoomDataSource.save(listCharacters)
        }
        return null
    }

    fun findItemCharacter(id: Int): Flow<Character> = getCharacterRoomDataSource.findById(id)
}