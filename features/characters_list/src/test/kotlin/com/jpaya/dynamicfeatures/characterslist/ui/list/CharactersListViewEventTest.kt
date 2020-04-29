package com.jpaya.dynamicfeatures.characterslist.ui.list

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CharactersListViewEventTest {

    lateinit var event: CharactersListViewEvent

    @Test
    fun setEventOpenCharacterDetail_ShouldSettledCorrectly() {
        val characterId = 1L
        event = CharactersListViewEvent.OpenCharacterDetail(characterId)

        val eventOpenDetail = event as CharactersListViewEvent.OpenCharacterDetail
        assertEquals(characterId, eventOpenDetail.id)
    }
}
