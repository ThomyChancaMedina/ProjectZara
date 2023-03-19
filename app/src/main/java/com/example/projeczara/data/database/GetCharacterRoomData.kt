package com.example.projeczara.data.database

import com.example.projeczara.data.domain.Character
import com.example.projeczara.data.database.character.CharacterDao
import com.example.projeczara.data.server.toDomainCharacter
import com.example.projeczara.datasource.GetCharacterRoomDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCharacterRoomData @Inject constructor(
    private val characterDao: CharacterDao
) : GetCharacterRoomDataSource {
    override var characters: Flow<List<Character>> = characterDao.getCharacters().map { it.toDomainCharacter() }

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { characterDao.characterCount() <= 0 }

    override fun findById(id: Int): Flow<Character> = characterDao.findById(id).map { it.toDomainDataBase() }

    override suspend fun save(characters: List<Character>) = withContext(Dispatchers.IO) {
        characterDao.insertCharacter(characters.map { it.toRoomCharacter() })
    }
}

