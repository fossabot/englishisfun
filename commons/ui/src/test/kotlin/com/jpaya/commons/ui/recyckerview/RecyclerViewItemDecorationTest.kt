package com.jpaya.commons.ui.recyckerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jpaya.commons.ui.recyclerview.RecyclerViewItemDecoration
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RecyclerViewItemDecorationTest {

    private lateinit var recyclerViewItemDecoration: RecyclerViewItemDecoration

    @MockK(relaxed = true)
    lateinit var viewDecorate: View
    @MockK(relaxed = true)
    lateinit var stateRecyclerView: RecyclerView.State
    @MockK(relaxed = true)
    lateinit var parentRecyclerView: RecyclerView
    @MockK(relaxed = true)
    lateinit var gridLayoutManager: GridLayoutManager
    @MockK(relaxed = true)
    lateinit var linearLayoutManager: LinearLayoutManager

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun configSpacingUnknownLayoutManager_ShouldNotConfigure() {
        val spacingPx = 10
        val outSpacing = Rect()

        recyclerViewItemDecoration = RecyclerViewItemDecoration(spacingPx)
        recyclerViewItemDecoration.getItemOffsets(
            outRect = outSpacing,
            view = viewDecorate,
            parent = parentRecyclerView,
            state = stateRecyclerView
        )

        assertEquals(0, outSpacing.bottom)
        assertEquals(0, outSpacing.top)
        assertEquals(0, outSpacing.left)
        assertEquals(0, outSpacing.right)
    }

    @Test
    fun configSpacingGridLayoutManager_WithOneElementInOneColumn_ShouldHaveExpectedSpace() {
        val spacingPx = 10
        val spanCount = 1
        val expectedSpacingsOnPosition = listOf(
            KRect(spacingPx, spacingPx, spacingPx, spacingPx)
        )
        every { parentRecyclerView.layoutManager } returns gridLayoutManager

        recyclerViewItemDecoration = RecyclerViewItemDecoration(spacingPx)
        expectedSpacingsOnPosition.forEachIndexed { index, expectedSpacing ->
            val outSpacing = generateFakeOffsetsGridLayout(
                position = index,
                spanCount = spanCount,
                itemsCount = expectedSpacingsOnPosition.size
            )

            assertRect(expectedSpacing, outSpacing)
        }
    }

    @Test
    fun configSpacingGridLayoutManager_WithThreeElementsInThreeColumns_ShouldHaveExpectedSpace() {
        val spacingPx = 20
        val spanCount = 3
        val expectedSpacingsOnPosition = listOf(
            KRect(spacingPx, spacingPx, 0, spacingPx),
            KRect(spacingPx, spacingPx, 0, spacingPx),
            KRect(spacingPx, spacingPx, spacingPx, spacingPx)
        )

        every { parentRecyclerView.layoutManager } returns gridLayoutManager

        recyclerViewItemDecoration = RecyclerViewItemDecoration(spacingPx)
        expectedSpacingsOnPosition.forEachIndexed { index, expectedSpacing ->
            val outSpacing = generateFakeOffsetsGridLayout(
                position = index,
                spanCount = spanCount,
                itemsCount = expectedSpacingsOnPosition.size
            )

            assertRect(expectedSpacing, outSpacing)
        }
    }

    @Test
    fun configSpacingGridLayoutManager_WithFourElementsInTwoColumns_ShouldHaveExpectedSpace() {
        val spacingPx = 30
        val spanCount = 2
        val expectedSpacingsOnPosition = listOf(
            KRect(spacingPx, spacingPx, 0, 0),
            KRect(spacingPx, spacingPx, spacingPx, 0),
            KRect(spacingPx, spacingPx, 0, spacingPx),
            KRect(spacingPx, spacingPx, spacingPx, spacingPx)
        )

        every { parentRecyclerView.layoutManager } returns gridLayoutManager

        recyclerViewItemDecoration = RecyclerViewItemDecoration(spacingPx)
        expectedSpacingsOnPosition.forEachIndexed { index, expectedSpacing ->
            val outSpacing = generateFakeOffsetsGridLayout(
                position = index,
                spanCount = spanCount,
                itemsCount = expectedSpacingsOnPosition.size
            )

            assertRect(expectedSpacing, outSpacing)
        }
    }

    @Test
    fun configSpacingHorizontalLinearLayoutManager_WithOneElement_ShouldHaveExpectedSpace() {
        val spacingPx = 10
        val expectedSpacingsOnPosition = listOf(
            KRect(spacingPx, spacingPx, spacingPx, spacingPx)
        )

        every { linearLayoutManager.canScrollHorizontally() } returns true
        every { parentRecyclerView.layoutManager } returns linearLayoutManager

        recyclerViewItemDecoration = RecyclerViewItemDecoration(spacingPx)
        expectedSpacingsOnPosition.forEachIndexed { index, expectedSpacing ->
            val outSpacing = generateFakeOffsetsLinearLayout(
                position = index,
                itemsCount = expectedSpacingsOnPosition.size
            )

            assertRect(expectedSpacing, outSpacing)
        }
    }

    @Test
    fun configSpacingHorizontalLinearLayoutManager_WithThreeElements_ShouldHaveExpectedSpace() {
        val spacingPx = 20
        val expectedSpacingsOnPosition = listOf(
            KRect(spacingPx, spacingPx, 0, spacingPx),
            KRect(spacingPx, spacingPx, 0, spacingPx),
            KRect(spacingPx, spacingPx, spacingPx, spacingPx)
        )

        every { linearLayoutManager.canScrollHorizontally() } returns true
        every { parentRecyclerView.layoutManager } returns linearLayoutManager

        recyclerViewItemDecoration = RecyclerViewItemDecoration(spacingPx)
        expectedSpacingsOnPosition.forEachIndexed { index, expectedSpacing ->
            val outSpacing = generateFakeOffsetsLinearLayout(
                position = index,
                itemsCount = expectedSpacingsOnPosition.size
            )

            assertRect(expectedSpacing, outSpacing)
        }
    }

    @Test
    fun configSpacingVerticalLinearLayoutManager_WithOneElement_ShouldHaveExpectedSpace() {
        val spacingPx = 10
        val expectedSpacingsOnPosition = listOf(
            KRect(spacingPx, spacingPx, spacingPx, spacingPx)
        )

        every { linearLayoutManager.canScrollVertically() } returns true
        every { parentRecyclerView.layoutManager } returns linearLayoutManager

        recyclerViewItemDecoration = RecyclerViewItemDecoration(spacingPx)
        expectedSpacingsOnPosition.forEachIndexed { index, expectedSpacing ->
            val outSpacing = generateFakeOffsetsLinearLayout(
                position = index,
                itemsCount = expectedSpacingsOnPosition.size
            )

            assertRect(expectedSpacing, outSpacing)
        }
    }

    @Test
    fun configSpacingVerticalLinearLayoutManager_WithThreeElements_ShouldHaveExpectedSpace() {
        val spacingPx = 20
        val expectedSpacingsOnPosition = listOf(
            KRect(spacingPx, spacingPx, spacingPx, 0),
            KRect(spacingPx, spacingPx, spacingPx, 0),
            KRect(spacingPx, spacingPx, spacingPx, spacingPx)
        )

        every { linearLayoutManager.canScrollVertically() } returns true
        every { parentRecyclerView.layoutManager } returns linearLayoutManager

        recyclerViewItemDecoration = RecyclerViewItemDecoration(spacingPx)
        expectedSpacingsOnPosition.forEachIndexed { index, expectedSpacing ->
            val outSpacing = generateFakeOffsetsLinearLayout(
                position = index,
                itemsCount = expectedSpacingsOnPosition.size
            )

            assertRect(expectedSpacing, outSpacing)
        }
    }

    @Test
    fun configSpacingNoScrollingLinearLayoutManager_WithOneElement_ShouldHaveExpectedSpace() {
        val spacingPx = 10
        val expectedSpacingsOnPosition = listOf(
            KRect(spacingPx, spacingPx, 0, 0)
        )

        every { parentRecyclerView.layoutManager } returns linearLayoutManager

        recyclerViewItemDecoration = RecyclerViewItemDecoration(spacingPx)
        expectedSpacingsOnPosition.forEachIndexed { index, expectedSpacing ->
            val outSpacing = generateFakeOffsetsLinearLayout(
                position = index,
                itemsCount = expectedSpacingsOnPosition.size
            )

            assertRect(expectedSpacing, outSpacing)
        }
    }

    // ============================================================================================
    //  Private methods utils
    // ============================================================================================

    private fun generateFakeOffsetsGridLayout(
        position: Int,
        spanCount: Int,
        itemsCount: Int
    ): Rect {
        every { gridLayoutManager.spanCount } returns spanCount
        every { stateRecyclerView.itemCount } returns itemsCount
        every {
            parentRecyclerView.getChildViewHolder(viewDecorate).adapterPosition
        } returns position

        val outDecorate = Rect()
        recyclerViewItemDecoration.getItemOffsets(
            outRect = outDecorate,
            view = viewDecorate,
            parent = parentRecyclerView,
            state = stateRecyclerView
        )

        return outDecorate
    }

    private fun generateFakeOffsetsLinearLayout(
        position: Int,
        itemsCount: Int
    ): Rect {
        every { stateRecyclerView.itemCount } returns itemsCount
        every {
            parentRecyclerView.getChildViewHolder(viewDecorate).adapterPosition
        } returns position

        val outDecorate = Rect()
        recyclerViewItemDecoration.getItemOffsets(
            outRect = outDecorate,
            view = viewDecorate,
            parent = parentRecyclerView,
            state = stateRecyclerView
        )

        return outDecorate
    }

    private fun assertRect(expected: KRect, actual: Rect) {
        assertEquals(expected.bottom, actual.bottom)
        assertEquals(expected.top, actual.top)
        assertEquals(expected.left, actual.left)
        assertEquals(expected.right, actual.right)
    }

    private data class KRect(
        val left: Int,
        val top: Int,
        val right: Int,
        val bottom: Int
    )
}
