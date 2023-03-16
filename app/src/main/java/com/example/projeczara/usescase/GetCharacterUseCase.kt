package com.example.projeczara.usescase

import com.example.projeczara.data.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val repository: CharacterRepository) {
    operator fun invoke() = repository.characters
}