package com.jpaya.dynamicfeatures.characterslist.ui.detail

import org.junit.Assert
import org.junit.Test

class CharacterDetailViewStateTest {

    lateinit var state: CharacterDetailViewState

    @Test
    fun setStateAsLoading_ShouldBeSettled() {
        state = CharacterDetailViewState.Loading

        Assert.assertTrue(state.isLoading())
        Assert.assertFalse(state.isError())
        Assert.assertFalse(state.isAddToFavorite())
        Assert.assertFalse(state.isAddedToFavorite())
        Assert.assertFalse(state.isAlreadyAddedToFavorite())
        Assert.assertFalse(state.isDismiss())
    }

    @Test
    fun setStateAsError_ShouldBeSettled() {
        state = CharacterDetailViewState.Error

        Assert.assertTrue(state.isError())
        Assert.assertFalse(state.isLoading())
        Assert.assertFalse(state.isAddToFavorite())
        Assert.assertFalse(state.isAddedToFavorite())
        Assert.assertFalse(state.isAlreadyAddedToFavorite())
        Assert.assertFalse(state.isDismiss())
    }

    @Test
    fun setStateAsAddToFavorite_ShouldBeSettled() {
        state = CharacterDetailViewState.AddToFavorite

        Assert.assertTrue(state.isAddToFavorite())
        Assert.assertFalse(state.isLoading())
        Assert.assertFalse(state.isError())
        Assert.assertFalse(state.isAddedToFavorite())
        Assert.assertFalse(state.isAlreadyAddedToFavorite())
        Assert.assertFalse(state.isDismiss())
    }

    @Test
    fun setStateAsAddedToFavorite_ShouldBeSettled() {
        state = CharacterDetailViewState.AddedToFavorite

        Assert.assertTrue(state.isAddedToFavorite())
        Assert.assertFalse(state.isLoading())
        Assert.assertFalse(state.isError())
        Assert.assertFalse(state.isAddToFavorite())
        Assert.assertFalse(state.isAlreadyAddedToFavorite())
        Assert.assertFalse(state.isDismiss())
    }

    @Test
    fun setStateAsAlreadyAddedToFavorite_ShouldBeSettled() {
        state = CharacterDetailViewState.AlreadyAddedToFavorite

        Assert.assertTrue(state.isAlreadyAddedToFavorite())
        Assert.assertFalse(state.isLoading())
        Assert.assertFalse(state.isError())
        Assert.assertFalse(state.isAddToFavorite())
        Assert.assertFalse(state.isAddedToFavorite())
        Assert.assertFalse(state.isDismiss())
    }

    @Test
    fun setStateAsDismiss_ShouldBeSettled() {
        state = CharacterDetailViewState.Dismiss

        Assert.assertTrue(state.isDismiss())
        Assert.assertFalse(state.isLoading())
        Assert.assertFalse(state.isError())
        Assert.assertFalse(state.isAddToFavorite())
        Assert.assertFalse(state.isAddedToFavorite())
        Assert.assertFalse(state.isAlreadyAddedToFavorite())
    }
}
