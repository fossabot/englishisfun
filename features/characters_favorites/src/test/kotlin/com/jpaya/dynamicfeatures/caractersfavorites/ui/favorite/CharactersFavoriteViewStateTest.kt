package com.jpaya.dynamicfeatures.caractersfavorites.ui.favorite

import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteViewState
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CharactersFavoriteViewStateTest {

    private lateinit var state: CharactersFavoriteViewState

    @Test
    fun setStateAsEmpty_ShouldBeSettled() {
        state = CharactersFavoriteViewState.Empty

        assertTrue(state.isEmpty())
        assertFalse(state.isListed())
    }

    @Test
    fun setStateAsListed_ShouldBeSettled() {
        state = CharactersFavoriteViewState.Listed

        assertTrue(state.isListed())
        assertFalse(state.isEmpty())
    }
}
