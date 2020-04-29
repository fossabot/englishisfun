package com.jpaya.commons.ui.bindings

import android.view.View
import com.jpaya.libraries.testutils.robolectric.TestRobolectric
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ViewBindingsTest : TestRobolectric() {

    private lateinit var view: View

    @Before
    fun setUp() {
        view = View(context)
    }

    @Test
    fun onViewVisibilityVisible_ShouldBeVisible() {
        view.visibility = View.VISIBLE
        assertTrue(view.visible)
        assertFalse(view.invisible)
        assertFalse(view.gone)
    }

    @Test
    fun onViewVisibilityInvisible_ShouldBeInvisible() {
        view.visibility = View.INVISIBLE
        assertTrue(view.invisible)
        assertFalse(view.visible)
        assertFalse(view.gone)
    }

    @Test
    fun onViewVisibilityGone_ShouldBeGone() {
        view.visibility = View.GONE
        assertTrue(view.gone)
        assertFalse(view.visible)
        assertFalse(view.invisible)
    }

    @Test
    fun forceViewVisibility_AsVisible() {
        view.visible = true
        assertTrue(view.visibility == View.VISIBLE)
    }

    @Test
    fun forceViewVisibility_AsNonVisible() {
        view.visible = false
        assertTrue(view.visibility == View.GONE)
    }

    @Test
    fun forceViewVisibility_AsInvisible() {
        view.invisible = true
        assertTrue(view.visibility == View.INVISIBLE)
    }

    @Test
    fun forceViewVisibility_AsNonInvisible() {
        view.invisible = false
        assertTrue(view.visibility == View.VISIBLE)
    }

    @Test
    fun forceViewVisibility_AsGone() {
        view.gone = true
        assertTrue(view.visibility == View.GONE)
    }

    @Test
    fun forceViewVisibility_AsNonGone() {
        view.gone = false
        assertTrue(view.visibility == View.VISIBLE)
    }
}
