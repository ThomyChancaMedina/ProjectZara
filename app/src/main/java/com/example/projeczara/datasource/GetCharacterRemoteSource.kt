package com.example.projeczara.datasource

import com.example.projeczara.data.domain.Character


interface GetCharacterRemoteSource {
    suspend fun requestCharacterFromRemote(): List<Character>
}
