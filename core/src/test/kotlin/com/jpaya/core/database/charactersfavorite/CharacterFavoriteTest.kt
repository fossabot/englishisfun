package com.jpaya.core.database.charactersfavorite

import com.jpaya.core.database.characterfavorite.CharacterFavorite
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterFavoriteTest {

    @Test
    fun createCharacterFavorite_ShouldAddCorrectAttributes() {
        val characterId = 0L
        val characterName = "A.I.M"
        val characterImageUrl = "http://i.annihil.us/535fecbbb9784.jpg"

        val characterFavorite = CharacterFavorite(
            id = characterId,
            name = characterName,
            imageUrl = characterImageUrl
        )

        assertEquals(characterId, characterFavorite.id)
        assertEquals(characterName, characterFavorite.name)
        assertEquals(characterImageUrl, characterFavorite.imageUrl)
    }
}
