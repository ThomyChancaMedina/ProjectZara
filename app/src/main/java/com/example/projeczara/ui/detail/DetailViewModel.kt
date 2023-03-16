package com.example.projeczara.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projeczara.data.domain.Character
import com.example.projeczara.usescase.FindItemCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val findItemCharacterUseCase: FindItemCharacterUseCase
) : ViewModel() {

    private val _itemCharacter = MutableStateFlow(UiDetailItem())
    val itemCharacter: StateFlow<UiDetailItem> = _itemCharacter.asStateFlow()

    fun fidCharacterForId(id:Int) {
        viewModelScope.launch {
            findItemCharacterUseCase(id)
                .catch { _itemCharacter.update { it } }
                .collect { character -> _itemCharacter.update { it.copy(itemCharacter = character) } }
        }
    }


    data class UiDetailItem(
        val itemCharacter: Character? = null
    )

}