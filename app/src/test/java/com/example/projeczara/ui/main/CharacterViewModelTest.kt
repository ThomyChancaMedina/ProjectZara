package com.example.projeczara.ui.main

import app.cash.turbine.test
import com.example.projeczara.testmock.mockCharacter
import com.example.projeczara.usescase.GetCharacterUseCase
import com.example.projeczara.usescase.RequestCharactersUseCase
import com.example.projeczara.util.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterViewModelTest {

    private lateinit var viewModel: CharacterViewModel

    @Mock
    lateinit var getCharacterUseCase: GetCharacterUseCase

    @Mock
    lateinit var requestCharactersUseCase: RequestCharactersUseCase

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        whenever(getCharacterUseCase.invoke()).thenReturn(flowOf(listOf(mockCharacter)))
        viewModel = CharacterViewModel(getCharacterUseCase, requestCharactersUseCase)
    }

    @Test
    fun `get all character from use case`() = runTest {
        val result = mutableListOf<CharacterViewModel.UiState>()
        val job = launch { viewModel.character.toList(result) }
        runCurrent()
        job.cancel()
        assertEquals(CharacterViewModel.UiState(character = listOf(mockCharacter)), result[0])
    }

    @Test
    fun `update progress when show progress and hidden progress`() = runTest {
        viewModel.onRequestAndGetAllCharacter()

        viewModel.character.test {

            assertEquals(CharacterViewModel.UiState(), awaitItem())
            assertEquals(CharacterViewModel.UiState(loading = false,character =  listOf(mockCharacter),error = null), awaitItem())
            assertEquals(CharacterViewModel.UiState(loading = true,character =  listOf(mockCharacter),error = null), awaitItem())
            assertEquals(CharacterViewModel.UiState(loading = false,character =  listOf(mockCharacter),error = null), awaitItem())
            cancel()
        }
    }

    @Test
    fun `character are requested when UI screen starts`()= runTest{
        viewModel.onRequestAndGetAllCharacter()
        runCurrent()
        verify(requestCharactersUseCase).invoke()
    }

}