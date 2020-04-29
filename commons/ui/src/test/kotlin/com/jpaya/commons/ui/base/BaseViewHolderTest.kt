package com.jpaya.commons.ui.base

import android.view.View
import androidx.databinding.ViewDataBinding
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BaseViewHolderTest {

    private val binding: ViewDataBinding = mock()
    private val rootView: View = mock()

    @Test
    fun createBaseViewHolder_ShouldInitializeCorrectly() {
        doReturn(rootView).whenever(binding).root

        val baseViewHolder = TestBaseViewHolder()

        assertEquals(binding, baseViewHolder.binding)
        assertEquals(binding.root, baseViewHolder.itemView)
    }

    inner class TestBaseViewHolder : BaseViewHolder<ViewDataBinding>(
        binding = binding
    )
}
