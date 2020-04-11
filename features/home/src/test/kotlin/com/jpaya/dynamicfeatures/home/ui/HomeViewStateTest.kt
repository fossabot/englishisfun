package com.jpaya.dynamicfeatures.home.ui

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class HomeViewStateTest {

    lateinit var state: HomeViewState

    @Test
    fun setStateAsFullScreen_ShouldBeSettled() {
        state = HomeViewState.FullScreen

        assertTrue(state.isFullScreen())
        assertFalse(state.isNavigationScreen())
    }

    @Test
    fun setStateAsNavigationScreen_ShouldBeSettled() {
        state = HomeViewState.NavigationScreen

        assertTrue(state.isNavigationScreen())
        assertFalse(state.isFullScreen())
    }
}
