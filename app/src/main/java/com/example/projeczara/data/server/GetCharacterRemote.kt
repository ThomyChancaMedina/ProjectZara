package com.example.projeczara.data.server

import com.example.projeczara.data.domain.Character
import com.example.projeczara.datasource.GetCharacterRemoteSource

class GetCharacterRemote(
    private val retrofitServer:  RetrofitServer
) : GetCharacterRemoteSource {

    override suspend fun requestCharacterFromRemote(): List<Character> =
        retrofitServer
            .server
            .getAllCharacter(19)
            .results
            .map { it.toDomainCharacter() }

}
