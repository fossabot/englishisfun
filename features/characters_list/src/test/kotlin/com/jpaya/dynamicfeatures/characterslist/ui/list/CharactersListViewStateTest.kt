package com.jpaya.dynamicfeatures.characterslist.ui.list

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class CharactersListViewStateTest {

    lateinit var state: CharactersListViewState

    @Test
    fun setStateAsRefreshing_ShouldBeSettled() {
        state = CharactersListViewState.Refreshing

        assertTrue(state.isRefreshing())
        assertFalse(state.isLoaded())
        assertFalse(state.isLoading())
        assertFalse(state.isAddLoading())
        assertFalse(state.isEmpty())
        assertFalse(state.isError())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsLoaded_ShouldBeSettled() {
        state = CharactersListViewState.Loaded

        assertTrue(state.isLoaded())
        assertFalse(state.isRefreshing())
        assertFalse(state.isLoading())
        assertFalse(state.isAddLoading())
        assertFalse(state.isEmpty())
        assertFalse(state.isError())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsLoading_ShouldBeSettled() {
        state = CharactersListViewState.Loading

        assertTrue(state.isLoading())
        assertFalse(state.isRefreshing())
        assertFalse(state.isLoaded())
        assertFalse(state.isAddLoading())
        assertFalse(state.isEmpty())
        assertFalse(state.isError())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsAddLoading_ShouldBeSettled() {
        state = CharactersListViewState.AddLoading

        assertTrue(state.isAddLoading())
        assertFalse(state.isRefreshing())
        assertFalse(state.isLoaded())
        assertFalse(state.isLoading())
        assertFalse(state.isEmpty())
        assertFalse(state.isError())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsEmpty_ShouldBeSettled() {
        state = CharactersListViewState.Empty

        assertTrue(state.isEmpty())
        assertFalse(state.isRefreshing())
        assertFalse(state.isLoaded())
        assertFalse(state.isLoading())
        assertFalse(state.isAddLoading())
        assertFalse(state.isError())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsError_ShouldBeSettled() {
        state = CharactersListViewState.Error

        assertTrue(state.isError())
        assertFalse(state.isRefreshing())
        assertFalse(state.isLoaded())
        assertFalse(state.isLoading())
        assertFalse(state.isAddLoading())
        assertFalse(state.isEmpty())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsAddError_ShouldBeSettled() {
        state = CharactersListViewState.AddError

        assertTrue(state.isAddError())
        assertFalse(state.isRefreshing())
        assertFalse(state.isLoaded())
        assertFalse(state.isLoading())
        assertFalse(state.isAddLoading())
        assertFalse(state.isEmpty())
        assertFalse(state.isError())
        assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsNoMoreElements_ShouldBeSettled() {
        state = CharactersListViewState.NoMoreElements

        assertTrue(state.isNoMoreElements())
        assertFalse(state.isRefreshing())
        assertFalse(state.isLoaded())
        assertFalse(state.isLoading())
        assertFalse(state.isAddLoading())
        assertFalse(state.isEmpty())
        assertFalse(state.isError())
        assertFalse(state.isAddError())
    }
}
