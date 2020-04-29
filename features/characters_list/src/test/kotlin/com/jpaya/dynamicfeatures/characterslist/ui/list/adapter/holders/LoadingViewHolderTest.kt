package com.jpaya.dynamicfeatures.characterslist.ui.list.adapter.holders

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import com.jpaya.dynamicfeatures.characterslist.databinding.ListItemLoadingBinding
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LoadingViewHolderTest {

    @MockK
    lateinit var view: View

    @MockK
    lateinit var layoutInflater: LayoutInflater

    @MockK(relaxed = true)
    lateinit var binding: ListItemLoadingBinding
    lateinit var viewHolder: LoadingViewHolder

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun createViewHolder_ShouldInitializeCorrectly() {
        mockkStatic(ListItemLoadingBinding::class)
        every { (binding as ViewDataBinding).root } returns view
        every { ListItemLoadingBinding.inflate(layoutInflater) } returns binding

        viewHolder = LoadingViewHolder(layoutInflater)

        assertEquals(binding, viewHolder.binding)
    }
}
