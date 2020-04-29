package com.jpaya.dynamicfeatures.abbreviations.ui

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AbbreviationsListViewStateTest {

    private lateinit var state: AbbreviationsListViewState

    @Test
    fun setStateAsRefreshing_ShouldBeSettled() {
        state = AbbreviationsListViewState.Refreshing

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
        state = AbbreviationsListViewState.Loaded

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
        state = AbbreviationsListViewState.Loading

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
        state = AbbreviationsListViewState.AddLoading

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
        state = AbbreviationsListViewState.Empty

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
        state = AbbreviationsListViewState.Error

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
        state = AbbreviationsListViewState.AddError

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
        state = AbbreviationsListViewState.NoMoreElements

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
