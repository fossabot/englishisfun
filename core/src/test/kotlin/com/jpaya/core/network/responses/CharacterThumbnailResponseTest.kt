package com.jpaya.core.network.responses

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CharacterThumbnailResponseTest {

    @Test
    fun createCharacterThumbnailResponse_ShouldAddCorrectAttributes() {
        val path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
        val extension = "jpg"

        val characterThumbnailResponse =
            CharacterThumbnailResponse(
                path = path,
                extension = extension
            )

        assertEquals(path, characterThumbnailResponse.path)
        assertEquals(extension, characterThumbnailResponse.extension)
    }
}
