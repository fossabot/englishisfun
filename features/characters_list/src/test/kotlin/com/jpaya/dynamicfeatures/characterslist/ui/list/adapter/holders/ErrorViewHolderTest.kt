package com.jpaya.dynamicfeatures.characterslist.ui.list.adapter.holders

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import com.jpaya.dynamicfeatures.characterslist.databinding.ListItemErrorBinding
import com.jpaya.dynamicfeatures.characterslist.ui.list.CharactersListViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ErrorViewHolderTest {

    @MockK
    lateinit var view: View

    @MockK
    lateinit var layoutInflater: LayoutInflater

    @MockK(relaxed = true)
    lateinit var binding: ListItemErrorBinding
    private lateinit var viewHolder: ErrorViewHolder

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun createViewHolder_ShouldInitializeCorrectly() {
        mockkStatic(ListItemErrorBinding::class)
        every { (binding as ViewDataBinding).root } returns view
        every { ListItemErrorBinding.inflate(layoutInflater) } returns binding

        viewHolder = ErrorViewHolder(layoutInflater)

        assertEquals(binding, viewHolder.binding)
    }

    @Test
    fun bindViewHolder_ShouldBindingDataVariable() {
        mockkStatic(ListItemErrorBinding::class)
        every { (binding as ViewDataBinding).root } returns view
        every { ListItemErrorBinding.inflate(layoutInflater) } returns binding

        val viewModel = mockk<CharactersListViewModel>()
        viewHolder = ErrorViewHolder(layoutInflater)
        viewHolder.bind(viewModel)

        verify { binding.viewModel = viewModel }
        verify { binding.executePendingBindings() }
    }
}
