package com.jpaya.core.network.responses

import com.jpaya.core.network.responses.CharacterResponse
import com.jpaya.core.network.responses.CharacterThumbnailResponse
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Test

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

        Assert.assertEquals(id, characterResponse.id)
        Assert.assertEquals(name, characterResponse.name)
        Assert.assertEquals(description, characterResponse.description)
        Assert.assertEquals(thumbnail, characterResponse.thumbnail)
    }
}
