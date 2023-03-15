package com.example.projeczara.data.server

import com.example.projeczara.data.server.model.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("character/")
    suspend fun getAllCharacter(
        @Query("page")
        page: Int
    ): CharacterResponseDto
}
