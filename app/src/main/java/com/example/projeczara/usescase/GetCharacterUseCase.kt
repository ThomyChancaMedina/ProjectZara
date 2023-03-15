package com.example.projeczara.usescase

import com.example.projeczara.data.CharacterRepository

class GetCharacterUseCase(private val repository: CharacterRepository) {
    operator fun invoke() = repository.characters
}