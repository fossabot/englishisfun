package com.jpaya.dynamicfeatures.abbreviations.ui

import org.junit.Assert
import org.junit.Test

class AbbreviationsListViewStateTest {

    lateinit var state: AbbreviationsListViewState

    @Test
    fun setStateAsRefreshing_ShouldBeSettled() {
        state = AbbreviationsListViewState.Refreshing

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
        state = AbbreviationsListViewState.Loaded

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
        state = AbbreviationsListViewState.Loading

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
        state = AbbreviationsListViewState.AddLoading

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
        state = AbbreviationsListViewState.Empty

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
        state = AbbreviationsListViewState.Error

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
        state = AbbreviationsListViewState.AddError

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
        state = AbbreviationsListViewState.NoMoreElements

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
