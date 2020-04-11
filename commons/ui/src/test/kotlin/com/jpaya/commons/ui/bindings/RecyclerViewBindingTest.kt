package com.jpaya.commons.ui.bindings

import androidx.recyclerview.widget.RecyclerView
import com.jpaya.commons.ui.recyclerview.RecyclerViewItemDecoration
import com.jpaya.libraries.testutils.robolectric.TestRobolectric
import org.hamcrest.Matchers.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class RecyclerViewBindingTest : TestRobolectric() {

    private lateinit var recyclerView: RecyclerView

    @Before
    fun setUp() {
        recyclerView = RecyclerView(context)
    }

    @Test
    fun setItemDecoration_ShouldAddProperlySpacing() {
        val spacingPx = 10f

        recyclerView.setItemDecorationSpacing(spacingPx)

        assertEquals(1, recyclerView.itemDecorationCount)

        val decoration = recyclerView.getItemDecorationAt(0)
        assertThat(decoration, instanceOf(RecyclerViewItemDecoration::class.java))
        assertEquals(spacingPx.toInt(), (decoration as RecyclerViewItemDecoration).spacingPx)
    }
}
