package com.example.projeczara.usescase

import com.example.projeczara.data.CharacterRepository
import javax.inject.Inject

class RequestCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke():Error? = repository.requestCharacters()
}