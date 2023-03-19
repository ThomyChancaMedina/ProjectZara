package com.example.projeczara.data.database

import com.example.projeczara.data.database.character.CharacterDao
import com.example.projeczara.data.server.toDomainCharacter
import com.example.projeczara.datasource.GetCharacterRoomDataSource
import com.example.projeczara.testmock.mockCharacter
import com.example.projeczara.util.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharacterRoomDataTest {

    @Mock
    lateinit var characterDao: CharacterDao

    @Mock
    lateinit var characterRoomData: GetCharacterRoomData

    @Before
    fun setUp() {
        characterRoomData = GetCharacterRoomData(characterDao)
    }



    @Test
    fun `getAllCharacter returns expected value`() = runTest {

        whenever(characterDao.getCharacters()).thenReturn(flowOf(listOf(mockCharacter).map { it.toRoomCharacter() }))

        characterRoomData.characters=characterDao.getCharacters().map { it.toDomainCharacter() }


        //then
        assertEquals(listOf(mockCharacter),characterRoomData.characters.single() )
    }

    @Test
    fun `insert data and verification base data`()= runTest {
        //given

        //when
        characterRoomData.save(listOf(mockCharacter))

        //then
        verify(characterDao).insertCharacter(listOf(mockCharacter).map { it.toRoomCharacter() } )
        verify(characterDao, times(1)).getCharacters()

    }

}