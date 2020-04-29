package com.jpaya.dynamicfeatures.characterslist.ui.list.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CharacterItemTest {

    @Test
    fun createCharacterItem_ShouldAddCorrectAttributes() {
        val id = 131231L
        val name = "A.I.M"
        val description = "AIM is a terrorist organization bent on destroying the world."
        val imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"

        val character = CharacterItem(
            id = id,
            name = name,
            description = description,
            imageUrl = imageUrl
        )

        assertEquals(id, character.id)
        assertEquals(name, character.name)
        assertEquals(description, character.description)
        assertEquals(imageUrl, character.imageUrl)
    }
}
