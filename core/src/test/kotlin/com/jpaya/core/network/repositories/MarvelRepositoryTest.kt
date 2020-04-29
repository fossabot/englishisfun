package com.jpaya.core.network.repositories

import com.jpaya.core.BuildConfig
import com.jpaya.core.network.repositiories.MarvelRepository
import com.jpaya.core.network.services.MarvelService
import com.jpaya.libraries.testutils.rules.InstantExecutorExtension
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

private const val API_PUBLIC_KEY = BuildConfig.MARVEL_API_KEY_PUBLIC

@ExtendWith(InstantExecutorExtension::class)
class MarvelRepositoryTest {

    @Mock
    lateinit var marvelService: MarvelService
    private lateinit var marvelRepository: MarvelRepository

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        marvelRepository = MarvelRepository(marvelService)
    }

    @Test
    fun getCharacters() = runBlocking {
        val charactersOffset = 0
        val charactersLimit = 20
        val (apiKey, hash, timestamp, offset, limit) =
            argumentCaptor<String, String, String, Int, Int>()

        marvelRepository.getCharacters(
            offset = charactersOffset,
            limit = charactersLimit
        )

        verify(marvelService).getCharacters(
            apiKey = apiKey.capture(),
            hash = hash.capture(),
            timestamp = timestamp.capture(),
            offset = offset.capture(),
            limit = limit.capture()
        )

        assertEquals(API_PUBLIC_KEY, apiKey.lastValue)
        assertEquals(charactersOffset, offset.lastValue)
        assertEquals(charactersLimit, limit.lastValue)
        assertNotNull(hash.lastValue)
        assertNotNull(timestamp.lastValue)
    }

    @Test
    fun getCharacter() = runBlocking {
        val characterId = 3L
        val (id, apiKey, hash, timestamp) = argumentCaptor<Long, String, String, String>()

        marvelRepository.getCharacter(characterId)

        verify(marvelService).getCharacter(
            id = id.capture(),
            apiKey = apiKey.capture(),
            hash = hash.capture(),
            timestamp = timestamp.capture()
        )

        assertEquals(characterId, id.lastValue)
        assertEquals(API_PUBLIC_KEY, apiKey.lastValue)
        assertNotNull(hash.lastValue)
        assertNotNull(timestamp.lastValue)
    }
}
