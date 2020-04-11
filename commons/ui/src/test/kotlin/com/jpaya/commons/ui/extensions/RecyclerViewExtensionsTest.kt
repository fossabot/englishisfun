package com.jpaya.commons.ui.extensions

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jpaya.libraries.testutils.robolectric.TestRobolectric
import org.hamcrest.Matchers.instanceOf
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class RecyclerViewExtensionsTest : TestRobolectric() {

    private lateinit var recyclerView: RecyclerView

    @Before
    fun setUp() {
        recyclerView = RecyclerView(context)
    }

    @Test
    fun obtainRecyclerLayoutManagerAsGridType() {
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        val gridLayoutManager = recyclerView.gridLayoutManager
        assertNotNull(gridLayoutManager)
        assertThat(gridLayoutManager, instanceOf(GridLayoutManager::class.java))
    }

    @Test
    fun obtainNullRecyclerGridLayoutManagerWithoutConfigured() {
        val gridLayoutManager = recyclerView.gridLayoutManager
        assertNull(gridLayoutManager)
    }

    @Test
    fun obtainRecyclerLayoutManagerAsLinearType() {
        recyclerView.layoutManager = LinearLayoutManager(context)

        val linearLayoutManager = recyclerView.linearLayoutManager
        assertNotNull(linearLayoutManager)
        assertThat(linearLayoutManager, instanceOf(LinearLayoutManager::class.java))
    }

    @Test
    fun obtainNullRecyclerLinearLayoutManagerWithoutConfigured() {
        val gridLayoutManager = recyclerView.linearLayoutManager
        assertNull(gridLayoutManager)
    }
}
