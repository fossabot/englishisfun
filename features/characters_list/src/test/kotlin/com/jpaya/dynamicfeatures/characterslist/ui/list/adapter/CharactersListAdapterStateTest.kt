package com.jpaya.dynamicfeatures.characterslist.ui.list.adapter

import org.junit.Assert
import org.junit.Test

class CharactersListAdapterStateTest {

    lateinit var state: CharactersListAdapterState

    @Test
    fun setStateAsAdded_ShouldBeSettled() {
        state = CharactersListAdapterState.Added

        Assert.assertTrue(state.hasExtraRow)
        Assert.assertTrue(state.isAdded())

        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isAddError())
        Assert.assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsAddLoading_ShouldBeSettled() {
        state = CharactersListAdapterState.AddLoading

        Assert.assertTrue(state.hasExtraRow)
        Assert.assertTrue(state.isAddLoading())

        Assert.assertFalse(state.isAdded())
        Assert.assertFalse(state.isAddError())
        Assert.assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsAddError_ShouldBeSettled() {
        state = CharactersListAdapterState.AddError

        Assert.assertTrue(state.hasExtraRow)
        Assert.assertTrue(state.isAddError())

        Assert.assertFalse(state.isAdded())
        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsNoMore_ShouldBeSettled() {
        state = CharactersListAdapterState.NoMore

        Assert.assertFalse(state.hasExtraRow)
        Assert.assertTrue(state.isNoMore())

        Assert.assertFalse(state.isAdded())
        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isAddError())
    }
}
