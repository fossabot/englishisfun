package com.jpaya.dynamicfeatures.characterslist.ui.list

import org.junit.Assert.assertEquals
import org.junit.Test

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
