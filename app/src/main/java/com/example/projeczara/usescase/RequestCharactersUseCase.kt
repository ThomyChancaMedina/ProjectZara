package com.example.projeczara.usescase

import com.example.projeczara.data.CharacterRepository

class RequestCharactersUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke():Error? = repository.requestCharacters()
}