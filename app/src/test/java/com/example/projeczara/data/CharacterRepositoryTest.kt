package com.example.projeczara.data

import com.example.projeczara.data.database.GetCharacterRoomData
import com.example.projeczara.data.server.GetCharacterRemote
import com.example.projeczara.testmock.mockCharacter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryTest {


    @Mock
    lateinit var repository: CharacterRepository

    @Mock
    lateinit var getCharacterRemote: GetCharacterRemote

    @Mock
    lateinit var getCharacterRoomData: GetCharacterRoomData

    private var expectedCharacter = flowOf(listOf(mockCharacter))

    @Before
    fun seUp() {
        whenever(getCharacterRoomData.characters).thenReturn(expectedCharacter)
        repository = CharacterRepository(getCharacterRoomData, getCharacterRemote)
    }

    @Test
    fun getAllCharacters() = runTest {
        val characters = repository.characters
        assertEquals(expectedCharacter, characters)
    }

    @Test
    fun requestCharacter() = runTest {
        //given
        val ip = "127.0.0.1"
        whenever(getCharacterRoomData.isEmpty()).thenReturn(true)
        whenever(getCharacterRemote.requestCharacterFromRemote()).thenReturn(listOf(mockCharacter))

        //when
        val error = repository.requestCharacters()

        //then
        assertNull(error)
    }

    @Test
    fun findCharacter() = runTest {
        //given
        val id = 1
        val expectedCharacter = mockCharacter

        whenever(getCharacterRoomData.findById(id)).thenReturn(flowOf(expectedCharacter))

        getCharacterRoomData.save(listOf(mockCharacter))

        //when
        val character = repository.findItemCharacter(id).first()

        //then
        assertEquals(expectedCharacter, character)
    }

}