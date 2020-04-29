package com.jpaya.dynamicfeatures.caractersfavorites.ui.favorite.adapter

import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.adapter.CharactersFavoriteTouchHelper
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CharactersFavoriteTouchHelperTest {

    @MockK(relaxed = true)
    lateinit var onSwiped: (Int) -> Unit
    private lateinit var touchHelper: CharactersFavoriteTouchHelper

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        touchHelper = CharactersFavoriteTouchHelper(onSwiped)
    }

    @Test
    fun createTouchHelper_ShouldInitializeCorrectly() {
        val recycleView = mockk<RecyclerView>()
        val viewHolder = mockk<RecyclerView.ViewHolder>()

        touchHelper.getDragDirs(recycleView, viewHolder).run {
            assertEquals(ACTION_STATE_IDLE, this)
        }

        touchHelper.getSwipeDirs(recycleView, viewHolder).run {
            assertEquals(LEFT or RIGHT, this)
        }
    }

    @Test
    fun moveTouchEvent_ShouldIgnoreIt() {
        val recycleView = mockk<RecyclerView>()
        val viewHolder = mockk<RecyclerView.ViewHolder>()
        val target = mockk<RecyclerView.ViewHolder>()

        touchHelper.onMove(recycleView, viewHolder, target).run {
            assertTrue(this)
        }

        verifyAll {
            recycleView wasNot Called
            viewHolder wasNot Called
            target wasNot Called
        }
    }

    @Test
    fun swipeTouchEvent_OnAlreadyRemovedItem_ShouldInvokeWithNoPosition() {
        val viewHolder = mockk<RecyclerView.ViewHolder>()
        val direction = LEFT

        every { viewHolder.adapterPosition } returns NO_POSITION

        touchHelper.onSwiped(viewHolder, direction)

        verify {
            onSwiped.invoke(NO_POSITION)
        }
    }

    @Test
    fun swipeTouchEvent_OnExistItem_ShouldInvokeWithPosition() {
        val viewHolder = mockk<RecyclerView.ViewHolder>()
        val direction = LEFT
        val swipePosition = 2

        every { viewHolder.adapterPosition } returns swipePosition

        touchHelper.onSwiped(viewHolder, direction)

        verify {
            onSwiped.invoke(swipePosition)
        }
    }
}
