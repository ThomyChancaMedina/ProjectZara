package com.example.projeczara.usescase

import com.example.projeczara.data.CharacterRepository
import com.example.projeczara.data.domain.Character
import kotlinx.coroutines.flow.Flow

class FindItemCharacterUseCase(private val repository: CharacterRepository) {
    operator fun invoke(id: Int): Flow<Character> = repository.findItemCharacter(id)
}