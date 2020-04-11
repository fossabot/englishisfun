package com.jpaya.core.network

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NetworkStateTest {

    @Test
    fun defaultInitializeSuccessState_ShouldHaveDefaultValues() {
        val networkState = NetworkState.Success()

        assertTrue(networkState.isSuccess())
        assertEquals(false, networkState.isAdditional)
        assertEquals(false, networkState.isEmptyResponse)
    }

    @Test
    fun initializeSuccessState_ShouldHaveCorrectValues() {
        val isAdditional = true
        val isEmptyResponse = true
        val networkState = NetworkState.Success(
            isAdditional = isAdditional,
            isEmptyResponse = isEmptyResponse
        )

        assertTrue(networkState.isSuccess())
        assertEquals(isAdditional, networkState.isAdditional)
        assertEquals(isEmptyResponse, networkState.isEmptyResponse)
    }

    @Test
    fun defaultInitializeLoadingState_ShouldHaveDefaultValues() {
        val networkState = NetworkState.Loading()

        assertTrue(networkState.isLoading())
        assertEquals(false, networkState.isAdditional)
    }

    @Test
    fun initializeLoadingState_ShouldHaveCorrectValues() {
        val isAdditional = true
        val networkState = NetworkState.Loading(isAdditional)

        assertTrue(networkState.isLoading())
        assertEquals(isAdditional, networkState.isAdditional)
    }

    @Test
    fun defaultInitializeErrorState_ShouldHaveDefaultValues() {
        val networkState = NetworkState.Error()

        assertTrue(networkState.isError())
        assertEquals(false, networkState.isAdditional)
    }

    @Test
    fun initializeErrorState_ShouldHaveDefaultValues() {
        val isAdditional = true
        val networkState = NetworkState.Error(isAdditional)

        assertTrue(networkState.isError())
        assertEquals(isAdditional, networkState.isAdditional)
    }
}
