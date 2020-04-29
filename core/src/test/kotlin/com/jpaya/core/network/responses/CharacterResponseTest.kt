package com.jpaya.core.network.responses

import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CharacterResponseTest {

    @Test
    fun createCharacterResponse_ShouldAddCorrectAttributes() {
        val id = 131231L
        val name = "A.I.M"
        val description = "AIM is a terrorist organization bent on destroying the world."
        val thumbnail: CharacterThumbnailResponse = mock()

        val characterResponse =
            CharacterResponse(
                id = id,
                name = name,
                description = description,
                thumbnail = thumbnail
            )

        assertEquals(id, characterResponse.id)
        assertEquals(name, characterResponse.name)
        assertEquals(description, characterResponse.description)
        assertEquals(thumbnail, characterResponse.thumbnail)
    }
}
