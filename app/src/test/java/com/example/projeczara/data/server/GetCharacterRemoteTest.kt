package com.example.projeczara.data.server

import com.example.projeczara.testmock.mockCharacter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharacterRemoteTest {
    @Mock
    lateinit var getCharacterRemote: GetCharacterRemote


    @Test
    fun getCharacterFromServer() = runTest {
        whenever(getCharacterRemote.requestCharacterFromRemote()).thenReturn(listOf(mockCharacter))

        val result = getCharacterRemote.requestCharacterFromRemote()


        assertNotNull(result)
        assertEquals(listOf(mockCharacter), result)

    }


}