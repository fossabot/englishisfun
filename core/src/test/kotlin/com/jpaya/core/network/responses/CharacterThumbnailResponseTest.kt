package com.jpaya.core.network.responses

import com.jpaya.core.network.responses.CharacterThumbnailResponse
import org.junit.Assert
import org.junit.Test

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

        Assert.assertEquals(path, characterThumbnailResponse.path)
        Assert.assertEquals(extension, characterThumbnailResponse.extension)
    }
}
