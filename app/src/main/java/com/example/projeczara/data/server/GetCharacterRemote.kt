package com.example.projeczara.data.server

import com.example.projeczara.data.domain.Character
import com.example.projeczara.datasource.GetCharacterRemoteSource
import javax.inject.Inject

class GetCharacterRemote @Inject constructor(
    private val remoteService: RemoteService
) : GetCharacterRemoteSource {

    override suspend fun requestCharacterFromRemote(): List<Character> =
        remoteService
            .getAllCharacter(19)
            .results
            .map { it.toDomainCharacter() }

}
