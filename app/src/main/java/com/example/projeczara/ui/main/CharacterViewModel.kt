package com.example.projeczara.ui.main

import androidx.lifecycle.*
import com.example.projeczara.data.domain.Character
import com.example.projeczara.usescase.GetCharacterUseCase
import com.example.projeczara.usescase.RequestCharactersUseCase

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val requestCharactersUseCase: RequestCharactersUseCase
) : ViewModel() {

    private val _character = MutableStateFlow(UiState())
    val character: StateFlow<UiState> get() = _character.asStateFlow()

    init {
        viewModelScope.launch {
            getCharacterUseCase()
                .catch {_character.update { it }  }
                .collect{character->_character.update { UiState(character = character) }}
        }
    }

    fun onRequestAndGetAllCharacter() {
        viewModelScope.launch {
            _character.value = _character.value.copy(loading = true)
            val error = requestCharactersUseCase.invoke()
            _character.value = _character.value.copy(loading = false, error = error)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val character: List<Character>? = null,
        val error: Error? = null
    )
}

@Suppress("UNCHECKED_CAST")
class CharacterViewModelFactory(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val requestCharactersUseCase: RequestCharactersUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterViewModel(getCharacterUseCase, requestCharactersUseCase) as T
    }
}