package com.jpaya.dynamicfeatures.home.ui

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HomeViewStateTest {

    private lateinit var state: HomeViewState

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
