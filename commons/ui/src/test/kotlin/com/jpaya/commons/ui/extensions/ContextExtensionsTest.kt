package com.jpaya.commons.ui.extensions

import android.content.Context
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ContextExtensionsTest {

    @MockK(relaxed = true)
    lateinit var context: Context

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getString_WhenIdIsNonNull_ReturnResource() {
        val resId = 0
        val expectedString = "test"

        every { context.getString(any()) } returns expectedString

        assertEquals(expectedString, context.getString(resId))
    }

    @Test
    fun getString_WhenIdIsNull_ReturnEmpty() {
        val resId = null
        val expectedString = ""

        assertEquals(expectedString, context.getString(resId))
    }
}
