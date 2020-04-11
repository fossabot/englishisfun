package com.jpaya.dynamicfeatures.characterslist.ui.list

import org.junit.Assert
import org.junit.Test

class CharactersListViewStateTest {

    lateinit var state: CharactersListViewState

    @Test
    fun setStateAsRefreshing_ShouldBeSettled() {
        state = CharactersListViewState.Refreshing

        Assert.assertTrue(state.isRefreshing())
        Assert.assertFalse(state.isLoaded())
        Assert.assertFalse(state.isLoading())
        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isEmpty())
        Assert.assertFalse(state.isError())
        Assert.assertFalse(state.isAddError())
        Assert.assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsLoaded_ShouldBeSettled() {
        state = CharactersListViewState.Loaded

        Assert.assertTrue(state.isLoaded())
        Assert.assertFalse(state.isRefreshing())
        Assert.assertFalse(state.isLoading())
        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isEmpty())
        Assert.assertFalse(state.isError())
        Assert.assertFalse(state.isAddError())
        Assert.assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsLoading_ShouldBeSettled() {
        state = CharactersListViewState.Loading

        Assert.assertTrue(state.isLoading())
        Assert.assertFalse(state.isRefreshing())
        Assert.assertFalse(state.isLoaded())
        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isEmpty())
        Assert.assertFalse(state.isError())
        Assert.assertFalse(state.isAddError())
        Assert.assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsAddLoading_ShouldBeSettled() {
        state = CharactersListViewState.AddLoading

        Assert.assertTrue(state.isAddLoading())
        Assert.assertFalse(state.isRefreshing())
        Assert.assertFalse(state.isLoaded())
        Assert.assertFalse(state.isLoading())
        Assert.assertFalse(state.isEmpty())
        Assert.assertFalse(state.isError())
        Assert.assertFalse(state.isAddError())
        Assert.assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsEmpty_ShouldBeSettled() {
        state = CharactersListViewState.Empty

        Assert.assertTrue(state.isEmpty())
        Assert.assertFalse(state.isRefreshing())
        Assert.assertFalse(state.isLoaded())
        Assert.assertFalse(state.isLoading())
        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isError())
        Assert.assertFalse(state.isAddError())
        Assert.assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsError_ShouldBeSettled() {
        state = CharactersListViewState.Error

        Assert.assertTrue(state.isError())
        Assert.assertFalse(state.isRefreshing())
        Assert.assertFalse(state.isLoaded())
        Assert.assertFalse(state.isLoading())
        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isEmpty())
        Assert.assertFalse(state.isAddError())
        Assert.assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsAddError_ShouldBeSettled() {
        state = CharactersListViewState.AddError

        Assert.assertTrue(state.isAddError())
        Assert.assertFalse(state.isRefreshing())
        Assert.assertFalse(state.isLoaded())
        Assert.assertFalse(state.isLoading())
        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isEmpty())
        Assert.assertFalse(state.isError())
        Assert.assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsNoMoreElements_ShouldBeSettled() {
        state = CharactersListViewState.NoMoreElements

        Assert.assertTrue(state.isNoMoreElements())
        Assert.assertFalse(state.isRefreshing())
        Assert.assertFalse(state.isLoaded())
        Assert.assertFalse(state.isLoading())
        Assert.assertFalse(state.isAddLoading())
        Assert.assertFalse(state.isEmpty())
        Assert.assertFalse(state.isError())
        Assert.assertFalse(state.isAddError())
    }
}
