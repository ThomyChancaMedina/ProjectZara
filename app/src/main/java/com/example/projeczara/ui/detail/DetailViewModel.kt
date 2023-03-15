package com.example.projeczara.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.projeczara.data.domain.Character
import com.example.projeczara.usescase.FindItemCharacterUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(
    idCharacter: Int,
    private val findItemCharacterUseCase: FindItemCharacterUseCase
) : ViewModel() {

    private val _itemCharacter = MutableStateFlow(UiDetailItem())
    val itemCharacter: StateFlow<UiDetailItem> = _itemCharacter.asStateFlow()

    init {
        viewModelScope.launch {
            findItemCharacterUseCase(idCharacter)
                .catch { _itemCharacter.update { it } }
                .collect { character -> _itemCharacter.update { it.copy(itemCharacter = character) } }
        }
    }

    data class UiDetailItem(
        val itemCharacter: Character? = null
    )

}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val idCharacter: Int,
    private val findItemCharacterUseCase: FindItemCharacterUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(idCharacter, findItemCharacterUseCase) as T
    }
}
