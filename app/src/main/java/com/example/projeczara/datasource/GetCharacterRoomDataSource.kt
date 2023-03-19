package com.example.projeczara.datasource


import com.example.projeczara.data.domain.Character
import kotlinx.coroutines.flow.Flow

interface GetCharacterRoomDataSource {

    var characters: Flow<List<Character>>

    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<Character>
    suspend fun save(characters: List<Character>)

}
