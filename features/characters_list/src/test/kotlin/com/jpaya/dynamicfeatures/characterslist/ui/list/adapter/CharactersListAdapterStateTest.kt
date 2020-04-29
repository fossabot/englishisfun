package com.jpaya.dynamicfeatures.characterslist.ui.list.adapter

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CharactersListAdapterStateTest {

    private lateinit var state: CharactersListAdapterState

    @Test
    fun setStateAsAdded_ShouldBeSettled() {
        state = CharactersListAdapterState.Added

        assertTrue(state.hasExtraRow)
        assertTrue(state.isAdded())

        assertFalse(state.isAddLoading())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsAddLoading_ShouldBeSettled() {
        state = CharactersListAdapterState.AddLoading

        assertTrue(state.hasExtraRow)
        assertTrue(state.isAddLoading())

        assertFalse(state.isAdded())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsAddError_ShouldBeSettled() {
        state = CharactersListAdapterState.AddError

        assertTrue(state.hasExtraRow)
        assertTrue(state.isAddError())

        assertFalse(state.isAdded())
        assertFalse(state.isAddLoading())
        assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsNoMore_ShouldBeSettled() {
        state = CharactersListAdapterState.NoMore

        assertFalse(state.hasExtraRow)
        assertTrue(state.isNoMore())

        assertFalse(state.isAdded())
        assertFalse(state.isAddLoading())
        assertFalse(state.isAddError())
    }
}
